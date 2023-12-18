package com.example.springkafka.request;

import lombok.Data;

@Data
public class PropertyPayload {

    private String op;
    private PropertyModel before;
    private PropertyModel after;
}
