package com.example.RestAPI.exception;

import lombok.Data;
@Data
public class UnableToFindTitle extends RuntimeException{
    private final String errorCode;
    public UnableToFindTitle(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Data
    public static class UsersNotFound extends RuntimeException{
    }
}
