package com.example.springkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringKafkaApplication implements CommandLineRunner {
	public static final Logger log= LoggerFactory.getLogger(SpringKafkaApplication.class);

	@Autowired
	private KafkaTemplate<String, String>kafkaTemplate;
	@KafkaListener(topics ="devs4j-topic", groupId ="devs4j-group")
	public void listen(String message) {
		System.out.println("Received Messasge in group foo: "+message);
		log.info("Message received {} ", message);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		kafkaTemplate.send("devs4j-topic","Send message");
	}
}
