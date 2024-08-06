package com.example.ExpenseTracker.Service;

public interface FindBy<T> {
    T findEntityById(Long id);
}

