package com.neel.kvstore.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.neel.kvstore.data.IDataAccessObject;
import com.neel.kvstore.data.InMemoryStore;

import net.sf.json.JSONObject;

public class KafkaSubscriber implements Runnable {
		   static IDataAccessObject dao = new InMemoryStore();
		   public void run() {
			   
		      
		      String topic = "kv";
		      String group = "group1";
		      Properties props = new Properties();
		      props.put("bootstrap.servers", "localhost:9092");
		      props.put("group.id", group);
		      props.put("enable.auto.commit", "true");
		      props.put("auto.commit.interval.ms", "1000");
		      props.put("session.timeout.ms", "30000");
		      props.put("key.deserializer",          
		         "org.apache.kafka.common.serialization.StringDeserializer");
		      props.put("value.deserializer", 
		         "org.apache.kafka.common.serialization.StringDeserializer");
		      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		      
		      consumer.subscribe(Arrays.asList(topic));
		      System.out.println("Subscribed to topic " + topic);
		    //  int i = 0;
		         
		      while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		            for (ConsumerRecord<String, String> record : records) {
		               System.out.printf("offset = %d, key = %s, value = %s, timestamp = %s\n", 
		               record.offset(), record.key(), record.value(), record.timestamp());
		            if(record.value() == null){
		            	dao.remove(record.key());
		            } else {
		            	JSONObject JSO = JSONObject.fromObject(record.value());
		            	dao.put(record.key(), JSO, record.timestamp());
		            }
		           }
		      }     
		   }  
		
}
