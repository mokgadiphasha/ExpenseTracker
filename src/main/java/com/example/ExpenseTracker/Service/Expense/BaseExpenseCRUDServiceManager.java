package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;

import java.util.List;

public interface BaseExpenseCRUDServiceManager {
    void saveExpense(Expense expense);
    void updateExpense(Long id, Expense expense);
    void deleteExpense(Long id);
    List<Expense> findAllExpensesByUser(Long userId);

}
