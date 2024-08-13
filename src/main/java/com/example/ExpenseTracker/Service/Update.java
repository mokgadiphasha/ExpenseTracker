package com.example.ExpenseTracker.Service;

public interface Update <T>{

    void updateExpense(Long id, T expense);
}
