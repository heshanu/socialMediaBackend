package com.example.RestAPI.exception;

import lombok.Data;
@Data
public class UnableToCreateBook extends RuntimeException{

    private final String errorCode;
    public UnableToCreateBook(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
