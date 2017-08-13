package com.neel.kvstore.init;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.neel.kvstore.kafka.KafkaPublisher;
import com.neel.kvstore.kafka.KafkaSubscriber;

public class Listener implements ServletContextListener{
	static KafkaSubscriber ks = new KafkaSubscriber();

public void contextDestroyed(ServletContextEvent arg0) {
System.out.println("ServletContextListener destroyed");
}

public void contextInitialized(ServletContextEvent arg0) {
System.out.println("ServletContextListener started");
new Thread(ks).start();
}
}
