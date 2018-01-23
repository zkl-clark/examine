package com.pingan.examine.utils;

import com.pingan.examine.start.ConfigFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * kafka工具类
 * Created by Administrator on 2017/12/16.
 */
public class KafKaUtil {
    private static KafkaProducer<String,String>producer = null;


    public static void sendDataToKafka(String topic,String key,String value){
        if(producer==null){
            initKafkaProducer();
        }
        ProducerRecord<String,String> message = new ProducerRecord(topic,key,value);
        producer.send(message);
    }

    /**
     * 初始化producer对象
     */
    private static void  initKafkaProducer(){
        Properties prop = new Properties();
        prop.put("bootstrap.servers", ConfigFactory.kafkaip+":"+ConfigFactory.kafkaport);
        prop.put("acks","0");
        prop.put("retries",0);
        prop.put("batch.size",16384);
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer(prop);

    }


}
