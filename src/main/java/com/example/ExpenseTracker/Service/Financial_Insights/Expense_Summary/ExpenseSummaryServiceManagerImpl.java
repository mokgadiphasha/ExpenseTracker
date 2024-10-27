package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;
import org.springframework.http.HttpStatus;
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
    public ExpenseSummaryResponse findSummaryBetweenTwoGivenDates(Long UserId, LocalDate start, LocalDate end) {
        if(start.isBefore(end)){
            Double totalExpense = expenseRepository
                    .summaryBetweenTwoGivenDatesQuery(start,end,UserId);
            return new ExpenseSummaryResponse(totalExpense);
        }
        else{
            throw new GlobalExceptionHandler("StartDate parameter" +
                    " should be earlier than EndDate.", HttpStatus.BAD_REQUEST,
                    "BAD_REQUEST");
        }
    }


    @Override
    public CategoryBreakdownResponse findCategoryBreakdown(Long userId) {
        return new CategoryBreakdownResponse(
                expenseRepository.categoryBreakdownQuery(userId));
    }


    @Override
    public MonthlySpendingResponse findMonthlySpending(LocalDate start, LocalDate end, Long userId) {
        if(!start.isAfter(end)){

            List<Month> months = expenseRepository
                    .monthlySpendingSummaryQuery(start,end,userId);
            return new MonthlySpendingResponse(months);
        } else{
            throw new GlobalExceptionHandler("StartDate parameter" +
                    " should be earlier than EndDate.", HttpStatus.BAD_REQUEST,
                    "BAD_REQUEST");
        }
    }

}
