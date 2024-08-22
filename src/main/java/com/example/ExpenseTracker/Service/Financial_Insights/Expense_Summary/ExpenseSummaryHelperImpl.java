package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseSummaryHelperImpl implements ExpenseSummaryHelper{
    @Override
    public List<YearMonth> getMonthsBetween(LocalDate start, LocalDate end) {
        List<YearMonth> months = new ArrayList<>();
        YearMonth startMonth = YearMonth.from(start);
        YearMonth endMonth = YearMonth.from(end);

        while (!startMonth.isAfter(endMonth)){
            months.add(startMonth);
            startMonth = startMonth.plusMonths(1);
        }
        return months;
    }
}
