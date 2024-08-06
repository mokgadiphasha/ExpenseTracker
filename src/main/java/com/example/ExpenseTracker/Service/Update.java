package com.example.ExpenseTracker.Service;

public interface Update <T>{

    void updateEntity(Long id,T entity);
}
