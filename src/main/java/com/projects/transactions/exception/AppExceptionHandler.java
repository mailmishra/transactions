package com.projects.transactions.exception;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), "Server-Fault");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleGenericException(ResourceNotFoundException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), "User-Fault");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
