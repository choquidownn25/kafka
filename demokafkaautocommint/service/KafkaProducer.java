package com.example.demokafkaautocommint.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    public static final Logger log= LoggerFactory.getLogger(KafkaProducer.class);
    private static final String TOPIC = "devs4j-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Producer Menssage : ", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
