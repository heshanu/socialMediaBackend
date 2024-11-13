package com.example.RestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String errorMessage;
    private String errorCode;
}
