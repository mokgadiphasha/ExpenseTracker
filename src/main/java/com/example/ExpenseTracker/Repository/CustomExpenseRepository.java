package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Month;

import java.time.LocalDate;
import java.util.List;

public interface CustomExpenseRepository {
    List<CategoryExpense> categoryBreakdownQuery(Long userId);
    Double sumAmountByDateBetweenAndUserId(LocalDate start, LocalDate end, Long userId);
    List<Month> sumAmountByMonthBetweenAndUserId(LocalDate start, LocalDate end, Long userId);
}
