package com.example.ExpenseTracker.Service;

public interface FindBy<T> {
    T findExpenseById(Long id);
}

