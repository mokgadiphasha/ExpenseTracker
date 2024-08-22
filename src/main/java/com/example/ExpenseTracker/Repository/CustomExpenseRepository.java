package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.CategoryExpense;

import java.time.LocalDate;
import java.util.List;

public interface CustomExpenseRepository {
    List<CategoryExpense> categoryBreakdownQuery(Long userId);
    Double sumAmountByDateBetweenAndUserId(LocalDate start, LocalDate end, Long userId);
}
