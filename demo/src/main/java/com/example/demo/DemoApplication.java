package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class DemoApplication {
//	public static final Logger log= LoggerFactory.getLogger(DemoApplication.class);
//	@KafkaListener(topics ="devs4j-topic", groupId ="consumer")
//	public void listen(String message) {
//		System.out.println("Received Messasge in group foo: "+message);
//		log.info("Message received {} ", message);
//	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
