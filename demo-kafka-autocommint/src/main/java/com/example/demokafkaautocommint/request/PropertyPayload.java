package com.example.demokafkaautocommint.request;

import lombok.Data;

@Data
public class PropertyPayload {

    private String op;
    private PropertyModel before;
    private PropertyModel after;
}
