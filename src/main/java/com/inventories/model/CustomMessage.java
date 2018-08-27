package com.inventories.model;

public class CustomMessage {
    private int statusCode;
    private String message;

    public CustomMessage(){
        this.setMessage("Empty");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
