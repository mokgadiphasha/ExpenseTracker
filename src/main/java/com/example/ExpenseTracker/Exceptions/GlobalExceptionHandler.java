package com.example.ExpenseTracker.Exceptions;

import org.springframework.http.HttpStatus;

public class GlobalExceptionHandler extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String error;
    public GlobalExceptionHandler(String message, HttpStatus httpStatus, String error){
        super(message);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }
}
