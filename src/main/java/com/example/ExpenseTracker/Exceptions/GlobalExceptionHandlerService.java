package com.example.ExpenseTracker.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerService {
    @ExceptionHandler(GlobalExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleGlobalCustomException(GlobalExceptionHandler ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(ex.getError(),ex.getMessage());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
