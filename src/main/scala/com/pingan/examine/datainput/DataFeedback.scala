package com.pingan.examine.datainput

import java.io.{File, FileOutputStream, OutputStreamWriter}

import com.alibaba.fastjson.{JSON, JSONObject}
import com.pingan.examine.start.ConfigFactory
import com.pingan.examine.utils.KafKaUtil
import org.apache.spark.rdd.RDD


/**
  * 数据反馈类
  * 该类主要用于把数据返回给接口调用
  * 并把数据做本系统的持久化保存
  * Created by Administrator on 2017/12/16.
  */
class DataFeedback {

def handleResultData(sourceData:RDD[String],adoptData:RDD[String]):Unit={
  var adoptArray:Array[String]=null
  var noadoptArray:Array[String]=null
  if(adoptData==null) {
    sourceData.foreach(rdd => {
      val d504bean: JSONObject = JSON.parseObject(rdd)
      KafKaUtil.sendDataToKafka(d504bean.get("d504_14").toString, d504bean.get("d504_01").toString, "error")

    })
    noadoptArray = sourceData.collect()
  }else{
    val noadoptData:RDD[String]=sourceData.subtract(adoptData)
    adoptData.foreach(rdd=>{
      val d504bean:JSONObject=JSON.parseObject(rdd)
      println("发送审核未通过数据到kafka")

      KafKaUtil.sendDataToKafka(d504bean.get("d504_14").toString,d504bean.get("d504_01").toString,"success")
    })
    noadoptData.foreach(rdd=>{
      val d504bean:JSONObject=JSON.parseObject(rdd)
      println("发送审核未通过数据到kafka")
      KafKaUtil.sendDataToKafka(d504bean.get("d504_14").toString, d504bean.get("d504_01").toString, "error")
    })
    adoptArray=noadoptData.collect()
    noadoptArray=noadoptData.collect()
  }
  val filename=System.currentTimeMillis()
  writeToLocal(ConfigFactory.localpath+File.separator+"adopt"+File.separator+filename+".txt",adoptArray)
  writeToLocal(ConfigFactory.localpath+File.separator+"noadopt"+File.separator+filename+".txt",noadoptArray)

}

  /**
    *保存内容到文件
    * @param path
    * @param dataArray
    */
  private def writeToLocal(path:String,dataArray:Array[String]):Unit={
    if(dataArray==null || dataArray.length<1){
      return
    }
    println("保存文件："+path)
    val tempFile:File = new File(path)
    if(tempFile.createNewFile()){
      val out = new FileOutputStream(path,true)
      val writer = new OutputStreamWriter(out,"utf-8")
      for(cnt<-0 until dataArray.length){
        writer.write(dataArray(cnt)+"\r\n")
      }
      writer.flush()
      out.close()
    }
  }



}
