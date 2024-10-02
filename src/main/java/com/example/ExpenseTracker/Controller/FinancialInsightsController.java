package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;
import com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary.ExpenseSummaryServiceManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/expenses/")
public class FinancialInsightsController {
    private final ExpenseSummaryServiceManager expenseSummaryServiceManager;

    public FinancialInsightsController(ExpenseSummaryServiceManager expenseSummaryServiceManager) {
        this.expenseSummaryServiceManager = expenseSummaryServiceManager;
    }

    @GetMapping("summary/{id}/{startDate}/{endDate}")
    public ExpenseSummaryResponse returnSummaryBetweenTwoDates(
            @PathVariable Long id,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate){

        return expenseSummaryServiceManager
                .summaryBetween(id,startDate,endDate);
    }


    @GetMapping("/category-breakdown/{userId}")
    public CategoryBreakdownResponse returnCategoryBreakdown(@PathVariable Long userId){
        return expenseSummaryServiceManager.categoryBreakdown(userId);
    }


    @GetMapping("/trends/monthly/{userId}/{startDate}/{endDate}")
    public MonthlySpendingResponse returnMonthlySpending(
            @PathVariable Long userId,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate){

        return expenseSummaryServiceManager
                .monthlySpending(startDate,endDate,userId);

    }

}
