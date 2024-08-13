package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;

import java.util.List;

public interface BaseExpenseCRUDServiceManager {
    Expense findExpenseById(Long id);
    void saveExpense(Expense expense);
    void updateExpense(Long id, Expense expense);
    List<Expense> findByFilter(Long id);
    void deleteExpense(Long id);

}
