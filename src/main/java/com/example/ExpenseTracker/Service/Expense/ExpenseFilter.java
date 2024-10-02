package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;

import java.util.List;

public interface ExpenseFilter {
    List<Expense> findByCategoryFilter(Long categoryId, Long userId);
    Expense findExpenseById(Long expenseId,Long UserId);
}
