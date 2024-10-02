package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;

import java.time.LocalDate;

public interface ExpenseSummaryServiceManager {
    ExpenseSummaryResponse findSummaryBetweenTwoGivenDates(Long userId, LocalDate start, LocalDate end);
    CategoryBreakdownResponse findCategoryBreakdown(Long userId);
    MonthlySpendingResponse findMonthlySpending(LocalDate start, LocalDate end, Long userId);

}
