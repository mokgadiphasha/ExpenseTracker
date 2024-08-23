package com.example.ExpenseTracker.Exceptions;

public class GlobalExceptionHandler extends RuntimeException{

    public GlobalExceptionHandler(String message){
        super(message);
    }
}
