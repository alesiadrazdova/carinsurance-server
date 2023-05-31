package com.bootcamp.carinsurance.pojo;

import java.util.Map;

public class ResponseWithMessage {
    private String message;

    public ResponseWithMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
