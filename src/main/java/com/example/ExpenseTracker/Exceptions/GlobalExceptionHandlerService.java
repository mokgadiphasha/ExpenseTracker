package com.example.ExpenseTracker.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerService {
    @ExceptionHandler(GlobalExceptionHandler.class)
    public ResponseEntity<String> handleGlobalCustomException(GlobalExceptionHandler ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
