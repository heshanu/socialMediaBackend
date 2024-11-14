package com.example.RestAPI.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {
    private String errorMessage;
    private String errorCode;

    // Builder pattern for creating instances
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String errorMessage;
        private String errorCode;

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponseDTO build() {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
            errorResponseDTO.errorMessage = this.errorMessage;
            errorResponseDTO.errorCode = this.errorCode;
            return errorResponseDTO;
        }
    }

    // Getters and setters

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
