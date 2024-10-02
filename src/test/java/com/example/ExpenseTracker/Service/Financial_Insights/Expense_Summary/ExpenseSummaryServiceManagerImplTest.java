package com.example.ExpenseTracker.Service.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class ExpenseSummaryServiceManagerImplTest {

    @Mock
    private ExpenseRepository repository;
    @InjectMocks
    private ExpenseSummaryServiceManagerImpl serviceManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGenerateFindSummaryBetweenTwoGivenDatesTwoGivenDates() {
        LocalDate start = LocalDate.of(2024,8,1);
        LocalDate end = LocalDate.of(2024,8,31);
        Double amountToBeReturned = 500.0;

        when(repository.summaryBetweenTwoGivenDatesQuery(start,end,1L))
                .thenReturn(amountToBeReturned);

        ExpenseSummaryResponse result = serviceManager
                .findSummaryBetweenTwoGivenDates(1L,start,end);

        assertEquals(amountToBeReturned,result.getExpenseTotal());

        verify(repository,times(1))
                .summaryBetweenTwoGivenDatesQuery(start,end,1L);

    }


    @Test
    void shouldNotGenerateFindSummaryBetweenTwoGivenDatesTwoGivenDates(){
        LocalDate end = LocalDate.of(2024,8,1);
        LocalDate start = LocalDate.of(2024,8,31);

        Exception exception = assertThrows(RuntimeException.class,
                () -> serviceManager.findSummaryBetweenTwoGivenDates(1L,start,end));

        String message = "An error occurred: " +
                "start date " + start.toString()+" is after end date "
                +end.toString()+".";
        assertEquals(message,exception.getMessage());
    }

    @Test
    void shouldReturnFindCategoryBreakdown() {
        CategoryExpense categoryExpenseOne = new CategoryExpense();
        categoryExpenseOne.setCategoryId(1L);
        categoryExpenseOne.setTotalExpense(500.0);

        CategoryExpense categoryExpenseTwo = new CategoryExpense();
        categoryExpenseTwo.setTotalExpense(300.0);
        categoryExpenseTwo.setCategoryId(2L);

        List<CategoryExpense> categoryExpenses = new ArrayList<>();
        categoryExpenses.add(categoryExpenseOne);
        categoryExpenses.add(categoryExpenseTwo);

        when(repository.categoryBreakdownQuery(1L))
                .thenReturn(categoryExpenses);

        CategoryBreakdownResponse result = serviceManager
                .findCategoryBreakdown(1L);

        assertEquals(categoryExpenses.size(),
                result.getCategoryExpenses().size());

        List<CategoryExpense> resultList = result.getCategoryExpenses();
        for (int i = 0; i < categoryExpenses.size(); i++) {
            assertEquals(categoryExpenses.get(i).getCategoryId(),
                    resultList.get(i).getCategoryId());

            assertEquals(categoryExpenses.get(i).getTotalExpense(),
                    resultList.get(i).getTotalExpense());
        }

        verify(repository,times(1))
                .categoryBreakdownQuery(1L);
    }

    @Test
    void shouldReturnFindMonthlySpending() {
        LocalDate start = LocalDate.of(2024,1,1);
        LocalDate end = LocalDate.of(2024,8,31);

        Month monthOne = new Month();
        monthOne.setYear(2024);
        monthOne.setMonth(8);
        monthOne.setTotalExpense(500.0);

        Month monthTwo = new Month();
        monthTwo.setYear(2024);
        monthTwo.setMonth(5);
        monthTwo.setTotalExpense(500.0);

        List<Month> months = new ArrayList<>();
        months.add(monthOne);
        months.add(monthTwo);

        when(repository.monthlySpendingSummaryQuery(start,end,1L))
                .thenReturn(months);

        MonthlySpendingResponse result = serviceManager
                .findMonthlySpending(start,end,1L);

        assertEquals(months.size(),result.getMonths().size());

        List<Month> monthsResults = result.getMonths();
        for (int i = 0; i < months.size(); i++) {
            assertEquals(months.get(i).getMonth(),
                    monthsResults.get(i).getMonth());

            assertEquals(months.get(i).getTotalExpense(),
                    monthsResults.get(i).getTotalExpense());

            assertEquals(months.get(i).getYear(),
                    monthsResults.get(i).getYear());

        }

        verify(repository,times(1))
                .monthlySpendingSummaryQuery(start,end,1L);

    }

    @Test
    void shouldNotReturnFindMonthlySpending(){
        LocalDate end = LocalDate.of(2024,1,1);
        LocalDate start = LocalDate.of(2024,8,31);

        Exception exception = assertThrows(RuntimeException.class,
                () -> serviceManager.findMonthlySpending(start,end,1L));

        String message = "An error occurred: " +
                "start date " + start.toString()+" is after end date "
                +end.toString()+".";

        assertEquals(message,exception.getMessage());


    }
}