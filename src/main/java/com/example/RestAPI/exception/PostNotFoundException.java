package com.example.RestAPI.exception;

import lombok.Data;

@Data
public class PostNotFoundException extends RuntimeException {
    private final String errorCode;
    public PostNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
