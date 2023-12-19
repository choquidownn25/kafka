package com.example.springkafka.utils;

public enum StringResponse {

    API_PREFIXS("debezium"),
    SEPARATORS(":"),
    KAFKABOOTSTRAP("localhost:29092"),
    LOCALHOST_LISTENER("localhost"),
    MESSAGE_KAFKA("Message info is :");

    private String name;

    private StringResponse(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
