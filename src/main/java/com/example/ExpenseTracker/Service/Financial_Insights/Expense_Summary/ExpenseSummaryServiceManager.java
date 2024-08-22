package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;

import java.time.LocalDate;

public interface ExpenseSummaryServiceManager {
    ExpenseSummaryResponse summaryBetween(Long userId, LocalDate start, LocalDate end);
    CategoryBreakdownResponse categoryBreakdown(Long userId);
    MonthlySpendingResponse monthlySpending(Long userId);

}
