package com.example.demokafkaautocommint.request;

import lombok.Data;


public class PropertyRequest {

    private String propertyKey;
    private String propertyValue;

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
