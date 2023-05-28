package com.bootcamp.carinsurance.pojo;

import java.util.Map;

public class ResponseWithMessage {
    private Map<String, String> message;

    public ResponseWithMessage(Map<String, String> message) {
        this.message = message;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
