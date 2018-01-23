package com.pingan.examine.datainput

import java.util.Timer

import com.alibaba.fastjson.{JSON, JSONObject}
import com.pingan.examine.start.ConfigFactory
import com.pingan.examine.utils.UpdateHdfsTask
import jodd.util.StringUtil
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.json.Test



/**
  * 数据获取类
  * Created by Administrator on 2017/12/11.
  */
object DataInput {
  private val dataFeedback=new DataFeedback()
  private val task=new UpdateHdfsTask()

  def main(args: Array[String]): Unit = {
    getDataForKafka
  }
  /**
    * 从kafka获取医疗机构要审核的报销信息
    */
  def getDataForKafka():Unit={
    ConfigFactory.initConfig()
    def conf1 = new SparkConf().setMaster(ConfigFactory.sparkstreammaster).setAppName(ConfigFactory.sparkstreamname)
    val streamingContext = new StreamingContext(conf1,Seconds(ConfigFactory.sparkseconds))
    val sparkSession=SparkSession.builder()
    sparkSession.master(ConfigFactory.sparksqlmaster).appName(ConfigFactory.sparksqlname)

    val rmessageRdd:ReceiverInputDStream[(String,String)] = KafkaUtils.createStream(streamingContext,ConfigFactory.kafkazookeeper,
      "user-behavior-topic-message-consumer-group",Map("reimnursement-message"->1),StorageLevel.MEMORY_ONLY)
    rmessageRdd.foreachRDD(rdd=>{
      rdd.persist()
      val count= rdd.count()
      println("本次处理数据数量："+count)
      if(count>0) {
        getDataFrameForDStream(rdd,sparkSession)
      }
    })
    //start() 返回后，由 awaitTermination() 阻塞住线程，以让这个 task 一直不退出，从而可以源源不断接收数据。
    startTinmerTask
    streamingContext.start()//启动receiver
    streamingContext.awaitTermination()//阻塞住线程
  }

  /**
    *处理单个Dstream中的RDD
    * @param rdd 本次要处理的RDD
    * @param sparkSession SparkSession.Builder
    */
  private def getDataFrameForDStream(rdd:RDD[(String,String)],sparkSession:SparkSession.Builder):Unit={

    val rdds:List[RDD[String]]=getDataFrame(rdd)
    val jsonSession:SparkSession=sparkSession.getOrCreate()
    val d504DataFrame:DataFrame=jsonSession.read.json(rdds(0))
    val d505DataFrame:DataFrame=jsonSession.read.json(rdds(1))
    d505DataFrame.persist()
    d504DataFrame.createOrReplaceTempView("d504table")
    d505DataFrame.createOrReplaceTempView("d505table")
    val newD504Rdd:RDD[String]=mainExamine(jsonSession,rdds(0))
    if(newD504Rdd==null){
      println("跳过第二次过滤，没有要处理的数据")
    }else {
      val newd504DataFrame: DataFrame = jsonSession.read.json(newD504Rdd)
      newd504DataFrame.createOrReplaceTempView("d504table")
      val endAdoptRdd: RDD[String] = repeatExamine(jsonSession, newD504Rdd)
      dataFeedback.handleResultData(rdds(0),endAdoptRdd)
    }
  }


  /**
    *把原始的RDD[(String,String)]转化为RDD[String],RDD中每行是一个完整的bean的json
    * @param rdd 原始的RDD
    * @return list第一个元素为D504表rdd,第二个为D505表的rdd
    */
  private  def getDataFrame(rdd:RDD[(String,String)]):List[RDD[String]]={
    val d504Rdd:RDD[String]=rdd.map{case(key,value)=>
      val beanjson:JSONObject = JSON.parseObject(value)
      beanjson.get("d504Bean").toString()
    }
        val d505Rdd:RDD[String] = rdd.flatMap{case(key,value)=>
          val beanjson:JSONObject =JSON.parseObject(value)//Object                                            8cd,
          var d505BeanList = beanjson.get("d505Beans").toString()//分离出d505Bean信息
          d505BeanList=d505BeanList.substring(1,d505BeanList.length-1)//提取字符（去掉了开头，末尾的"[","]"）
          val d505Array = StringUtil.split(d505BeanList,"},{")//定义内容用}{分割开
          d505Array(0)=d505Array(0)+"{"
          d505Array(d505Array.length-1)="}"+d505Array(d505Array.length-1)
              if(d505Array.length>2){
                var cnt = 1
                for(cnt<-1 to d505Array.length-1){
                  d505Array(cnt)="{"+d505Array(cnt)+"}"
                }
            }
            d505Array
    }
    List(d504Rdd,d505Rdd)
  }

  /**
    * 住院主审核过滤程序
    * @param jsonSession SparkSession
    * @param d504rdd D504表的rdd
    * @return 过滤之后通过审核新的 d504rdd
    */
  private def mainExamine(jsonSession:SparkSession,d504rdd:RDD[String]):RDD[String]={
    //应该是having cnt<=3,但是测试数据中没有小于3的，暂时改为15
    val sql =
      """select t1.d504_01,count(t1.d504_01)as cnt from d504table t1,d505table t2
        |where t1.d504_13<=14
        |and t2.e505_02=0
        |and t1.d504_01=t2.d505_01
        |and t1.d504_12=t2.d505_13
        |group by t1.d504_01 having cnt<=15
      """.stripMargin
    val rows:DataFrame=jsonSession.sql(sql)
    println("================第一次过滤结果=============")
    rows.show(1000)
    println("===========================================")
    val rowArray:Array[Row]=rows.collect()
    var adoptList:List[String]=List()
    for(cnt<-0 until rowArray.length){
      adoptList=adoptList++:List(rowArray(cnt).getString(0))
    }
    if(adoptList.size<1){
      return null
    }else {
      val newd504rdd: RDD[String] = d504rdd.filter(line => {
        val d504id = JSON.parseObject(line).get("d504_01").toString()
        if (adoptList.contains(d504id)) {
          true
        } else {
          false
        }
      })
      newd504rdd
    }
  }

  /**
    * 重复住院过滤
    * @param jsonSession SparkSession
    * @param newD504Rdd 第一次过滤后剩余的符合条件的记录集合
    * @return 第二次过滤后剩余的符合条件的记录集合
    */
  private def repeatExamine(jsonSession:SparkSession,newD504Rdd:RDD[String]):RDD[String]={
  //正式运行 jsonSession.read.json("hdfs://examine/hisd504")

  val hisd504DataFrame:DataFrame = jsonSession.read.json("hdfs://master:9000/examine/hisd504.txt")
  val d506DataFrame:DataFrame = jsonSession.read.json("hdfs://master:9000/examine/d506.txt")
  hisd504DataFrame.createOrReplaceTempView("hisd504table")
  d506DataFrame.createOrReplaceTempView("d506table")

  val sql=
    """select distinct t1.d504_01 from d504table t1,d505table t2,hisd504table t3,d506table t4
      |where t1.d504_01 = t2.d505_01 and t1.d504_02 = t3.d505_02 and t1.d504_02 = t4.e506_01
      |and(
      |(t1.D504_14 = t3.D504_14 and (case(unix_timestamp(t1.D504_11,'dd-MM-yyyy') as long)-cast(unix_timestamp(t3.D504_12,'dd-MM-yyyy') as long))<=604800000)
      |or (t1.D504_14 = t3.D504_14 and (case(unix_timestamp(t1.S504_11,'dd-MM-yyyy') as long)-case(unix_timestamp(t3.D504_12,'dd-MM-yyyy') as long))<=604800000
      |and t1.D504_21 =t3.D504_21 and(t3.D504_20='0' or t3.D504_20=''))
      |or ((select sum(D506_24) from d506table where e506_01 = t1.d504_02 group by e506_01)
      |+(select sum(t5.e505_06) from d505table t5 where t5.d504_01 = t1.d504_01 group by D504_01)>300000)
      |or t4.D506_25!=0)
    """.stripMargin
    val sql2 =
      """
        |SELECT DISTINCT t1.d504_01 FROM d504Table t1,d505Table t2,hisD504Table t3,d506Table t4
        |WHERE t1.d504_01 = t2.d505_01 AND t1.d504_02 = t3.d504_02 AND t1.d504_02 = t4.e506_01
        |AND (
        |(t1.d504_14 = t3.d504_14 AND CAST(UNIX_TIMESTAMP(t1.d504_11,'dd-MM-yyyy') AS LONG) - CAST(UNIX_TIMESTAMP(t1.d504_12,'dd-MM-yyyy') AS LONG) <= 604800000)
        |OR (t1.d504_14 != t3.d504_14 AND CAST(UNIX_TIMESTAMP(t1.d504_11,'dd-MM-yyyy') AS LONG) - CAST(UNIX_TIMESTAMP(t1.d504_12,'dd-MM-yyyy') AS LONG) <= 604800000 AND t1.d504_21 = t3.d504_21 AND (t3.d504_20 = '0' OR t3.d504_20 = ''))
        |OR ((SELECT SUM(d506_24) FROM d506Table WHERE e506_01 = t1.d504_02 GROUP BY e506_01) + (SELECT SUM(t5.e505_06) FROM d505Table t5 WHERE t5.d505_01 = t1.d504_01 GROUP BY t5.d505_01) > 300000)
        |OR t4.D506_25 != 0
        |)
      """.stripMargin

    val rows:DataFrame=jsonSession.sql(sql2)
    println("================第二次过滤结果=============")
    rows.show(10)
    println("===========================================")
    val rowArray:Array[Row]=rows.collect()
    var adoptList:List[String]=List()
    for(cnt<-0 until rowArray.length){
      adoptList=adoptList++:List(rowArray(cnt).getString(0))
    }

  val endd504rdd:RDD[String]=newD504Rdd.filter(line=>{
    val d504id = JSON.parseObject(line).get("d504_01").toString()
    if(adoptList.contains(d504id)){
      false
    }else{
      true
    }
  })
  endd504rdd
}

  def startTinmerTask(): Unit ={
    val time=new Timer()
    time.schedule(task,0,1000*30)
  }


}
