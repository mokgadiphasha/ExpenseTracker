package com.example.ExpenseTracker.Service.Financial_Insights;

import java.time.LocalDate;

public interface ExpenseSummary {
    void summaryBetween(Long UserId,LocalDate start,LocalDate end);
    void categoryBreakdown(Long userId,Long categoryId);
    void monthlySpending(Long userId);

}
