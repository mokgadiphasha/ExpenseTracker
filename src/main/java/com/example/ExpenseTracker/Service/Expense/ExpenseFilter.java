package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;

import java.util.List;

public interface ExpenseFilter {
    List<Expense> findByFilter(Long id);
    Expense findExpenseById(Long id);
}
