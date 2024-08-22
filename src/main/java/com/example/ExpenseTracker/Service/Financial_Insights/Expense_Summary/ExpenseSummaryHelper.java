package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface ExpenseSummaryHelper {
    List<YearMonth> getMonthsBetween(LocalDate start,LocalDate end);
}
