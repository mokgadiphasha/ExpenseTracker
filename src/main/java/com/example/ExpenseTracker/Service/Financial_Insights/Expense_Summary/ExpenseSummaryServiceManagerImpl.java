package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseSummaryServiceManagerImpl implements ExpenseSummaryServiceManager {
    private final ExpenseRepository expenseRepository;

    public ExpenseSummaryServiceManagerImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public ExpenseSummaryResponse summaryBetween(Long UserId, LocalDate start, LocalDate end) {
        Double totalExpense = expenseRepository
                .sumAmountByDateBetweenAndUserId(start,end,UserId);
               return new ExpenseSummaryResponse(totalExpense);

    }


    @Override
    public CategoryBreakdownResponse categoryBreakdown(Long userId) {
        return new CategoryBreakdownResponse(
                expenseRepository.categoryBreakdownQuery(userId));
    }


    @Override
    public MonthlySpendingResponse monthlySpending(LocalDate start, LocalDate end, Long userId) {
        List<Month> months = expenseRepository
                .sumAmountByMonthBetweenAndUserId(start,end,userId);
        return new MonthlySpendingResponse(months);

    }

}
