package com.example.demo.devs.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "desvs4j-topic", groupId = "group_id")
    public void consumeMessage(Object message) {
        // Your logic to handle the received message
        System.out.println("Received message: " + message);
    }
}
