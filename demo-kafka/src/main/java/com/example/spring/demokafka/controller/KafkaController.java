package com.example.spring.demokafka.controller;

import com.example.spring.demokafka.request.PropertyRequest;
import com.example.spring.demokafka.service.KafkaProducerService;
import com.example.spring.demokafka.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;
    private final PropertyService propertyService;
    @Autowired
    public KafkaController(KafkaProducerService producerService, PropertyService propertyService) {
        this.producerService = producerService;
        this.propertyService = propertyService;
    }

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestBody String message) {
        producerService.sendMessage(message);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createProperty(@RequestBody PropertyRequest propertyRequest){
        String datos = propertyService.createProperty(propertyRequest.getPropertyKey(),propertyRequest.getPropertyValue());
        return new ResponseEntity<String>(datos, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProperty(@RequestBody PropertyRequest propertyRequest){
        propertyService.updateProperty(propertyRequest);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/get/{key}")
    public ResponseEntity<String> getProperty(@RequestParam(name = "key") String key) throws InterruptedException {
        return new ResponseEntity<String>(propertyService.getProperty(key),HttpStatus.OK);
    }

}
