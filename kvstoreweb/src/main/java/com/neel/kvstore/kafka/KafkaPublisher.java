package com.neel.kvstore.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import org.apache.kafka.clients.producer.ProducerRecord;

import net.sf.json.JSONObject;

public class KafkaPublisher {
	
	public void publish(String key, JSONObject json){
	      
	   
	      //Assign topicName to string variable
	      String topicName = "test";
	      
	      // create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers", "localhost:9092");
	      
	      //Set acknowledgements for producer requests.      
	      props.put("acks", "all");
	      
	      //If the request fails, the producer can automatically retry,
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	         
	      props.put("value.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	      
	      Producer<String, String> producer = new KafkaProducer
	         <String, String>(props);
	            

	         if(json != null){
	         producer.send(new ProducerRecord<String, String>(topicName, 
	        		 key, json.toString()));
	      } else {
	    	  producer.send(new ProducerRecord<String, String>(topicName, 
		        		 key,null));
	      }
	               System.out.println("Message sent successfully");
	               producer.close();
	   }

}