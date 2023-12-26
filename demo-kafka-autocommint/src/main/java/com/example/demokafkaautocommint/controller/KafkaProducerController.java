package com.example.demokafkaautocommint.controller;

import com.example.demokafkaautocommint.request.PropertyRequest;
import com.example.demokafkaautocommint.service.KafkaProducer;
import com.example.demokafkaautocommint.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;
    private final PropertyService propertyService;
    @Autowired
    public KafkaProducerController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/produce")
    public void produceMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createProperty(@RequestBody PropertyRequest propertyRequest){
        kafkaProducer.sendMessage(propertyRequest.getPropertyKey() +"-"+ propertyRequest.getPropertyValue()); ;
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
