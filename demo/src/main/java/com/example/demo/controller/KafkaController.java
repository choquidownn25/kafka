package com.example.demo.controller;

import com.example.demo.devs.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/produce")
    public String produceMessage(@RequestBody Object message) {
        String topic ="devs4j-topic";
        kafkaProducer.sendMessage(topic, message);
        return "Message sent to Kafka topic successfully!";
    }
}
