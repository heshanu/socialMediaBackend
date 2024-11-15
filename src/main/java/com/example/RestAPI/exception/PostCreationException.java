package com.example.RestAPI.exception;

import lombok.Data;

@Data
public class PostCreationException extends RuntimeException{
    private final String errorCode;

    public PostCreationException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
