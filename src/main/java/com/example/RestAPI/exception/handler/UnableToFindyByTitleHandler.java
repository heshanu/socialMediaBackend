package com.example.RestAPI.exception.handler;

import com.example.RestAPI.dto.ErrorResponseDTO;
import com.example.RestAPI.exception.UnableToCreateBook;
import com.example.RestAPI.exception.UnableToFindTitle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnableToFindyByTitleHandler {
    @ExceptionHandler(UnableToFindTitle.class)
    public ResponseEntity<ErrorResponseDTO> handleControllerNotFound(UnableToCreateBook exception) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
