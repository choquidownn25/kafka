package com.example.springkafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final String topicName = "devs4j-topic";

    @KafkaListener(topics = topicName, groupId = "consumer")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received Message: " + record.value());

    }
}

