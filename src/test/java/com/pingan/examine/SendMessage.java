package com.pingan.examine;

import com.alibaba.fastjson.JSON;
import com.pingan.examine.bean.D504Bean;
import com.pingan.examine.bean.D505Bean;
import com.pingan.examine.bean.RecordBean;
import com.pingan.examine.start.ConfigFactory;
import jodd.util.StringUtil;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/12/12.
 */
public class SendMessage {

    public static void main(String[] args) {

        ConfigFactory.initConfig();
        List<D504Bean> d504BeanList= null;
        Map<String,List<D505Bean>>d505Beanmap=null;
        try {
            d504BeanList = getD504Bean();
            System.out.println(d504BeanList.size());
            d505Beanmap=getD505Bean();
            System.out.println(d505Beanmap.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties props=new Properties();
        props.put("bootstrap.servers",ConfigFactory.kafkaip+":"+ConfigFactory.kafkaport);
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> kafkaProducer= new KafkaProducer(props);
        int cnt=0;
        while (true){
            D504Bean d504Bean=d504BeanList.get(cnt);
            RecordBean bean= new RecordBean(d504Bean,d505Beanmap.get(d504Bean.getD504_01()));
            String datamessage=JSON.toJSONString(bean);
            ProducerRecord<String,String> message=new ProducerRecord<String, String>("reimnursement-message",datamessage);
            kafkaProducer.send(message);
            System.out.println("发送信息："+cnt+","+datamessage);
            ThreadUtil.sleep(1000*1);
            cnt++;
            if (cnt==d504BeanList.size()){
                cnt=0;
            }
        }
    }

    /**
     * 获取所有的
     * @returnD504Bean组装好的
     * @throws IOException
     */
    private static  List<D504Bean>getD504Bean() throws IOException {
        List<List<String>> d504List=readfile("d504.sql");
        List<D504Bean>returnvalue=new ArrayList<D504Bean>(d504List.size());
        for (List<String>oneData:d504List){
            if (oneData!=null&&oneData.size()==65){
                List<String> newList=new ArrayList<String>(oneData.size());
                for (String str:oneData){
                    newList.add(str.trim().replaceAll("'",""));
                }
                D504Bean bean  =new D504Bean(newList);
                returnvalue.add(bean);
            }
        }
        return returnvalue;
    }
    private static Map<String,List<D505Bean>>getD505Bean() throws IOException {
        List<List<String>>d505List=readfile("d505.sql");
        Map<String,List<D505Bean>>returnvalue=new HashMap<String, List<D505Bean>>(d505List.size());
        for (List<String>oneData:d505List){
            if (oneData!=null&&oneData.size()==44){
                List<String>newList=new ArrayList<String>(oneData.size());
                for (String str:oneData){
                    newList.add(str.trim().replaceAll("'",""));
                }
                D505Bean bean=new D505Bean(newList);
                if (returnvalue.get(bean.getD505_01())==null){
                    List<D505Bean>tempList=new ArrayList<D505Bean>();
                    tempList.add(bean);
                    returnvalue.put(bean.getD505_01(),tempList);
                }else {
                    List<D505Bean>tempList=returnvalue.get(bean.getD505_01());
                    tempList.add(bean);
                }
            }
        }
        return returnvalue;
    }
    /**
     * 读取测试数据文件到内存
     * @param name  要读取的文件
     * @return  读取到的数据文件
     * @throws IOException
     */
    private static List<List<String>> readfile(String name) throws IOException {
        List<List<String>> returnValue = new ArrayList<List<String>>();
        InputStream inputStream = new FileInputStream("D:\\medicalCare\\"+name);
        Reader reader = new InputStreamReader(inputStream,"utf-8");
        LineNumberReader lnr = new LineNumberReader(reader);
        while(true){
            String str = lnr.readLine();
            if (str ==null){
                break;
            }
            if (StringUtils.startsWith(str,"values")){
                List<String>list= new ArrayList<String>();
                String str1= StringUtil.substring(str,8,str.length()-2);
                String[] array=str.split("to_date\\(");
                list.addAll(Arrays.asList(array[0].split(",")));
                if (list.get(list.size()-1).equals(" ")){
                    list.remove(list.size()-1);
                }
                for (int cnt=1;cnt<array.length;cnt++){
                    List<String>tempList=new ArrayList<String>(Arrays.asList(array[cnt].split(",")));
                    tempList.remove(1);
                    if (tempList.get(tempList.size()-1).equals(" ")){
                        tempList.remove(tempList.size()-1);
                    }
                    list.addAll(tempList);
                }
                returnValue.add(list);
            }
        }
        inputStream.close();
        return returnValue;
    }
}
