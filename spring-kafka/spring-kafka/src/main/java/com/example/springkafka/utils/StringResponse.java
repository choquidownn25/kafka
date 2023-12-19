package com.example.springkafka.utils;

public enum StringResponse {

    API_PREFIX("debezium"),
    KAFKABOOTSTRAP("localhost:29092"),
    MESSAGE_KAFKA("Message info is :");
    private String name;

    private StringResponse(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
