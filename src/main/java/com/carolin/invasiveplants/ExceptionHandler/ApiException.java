package com.carolin.invasiveplants.ExceptionHandler;

import org.springframework.http.HttpStatus;

// Simple reusable exception that carries HTTP status and message
// Example: throw new ApiException("user not found", HttpStatus.NOT_FOUND);
// Made to replace multiple exception classes
public class ApiException extends RuntimeException {

    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
