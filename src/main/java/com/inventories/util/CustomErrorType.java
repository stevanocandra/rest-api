package com.inventories.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ResponseEntity returnResponsEntityError(String message){
        return new ResponseEntity(new CustomErrorType("An error occurred: " + message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}