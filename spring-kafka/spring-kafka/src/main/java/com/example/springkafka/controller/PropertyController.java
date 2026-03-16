package com.example.springkafka.controller;

import com.example.springkafka.request.PropertyRequest;
import com.example.springkafka.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<String> createProperty(@RequestBody PropertyRequest propertyRequest){
        String datos = propertyService.createProperty(propertyRequest.getPropertyKey(),propertyRequest.getPropertyValue());
        return new ResponseEntity<String>(datos,HttpStatus.CREATED);
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
