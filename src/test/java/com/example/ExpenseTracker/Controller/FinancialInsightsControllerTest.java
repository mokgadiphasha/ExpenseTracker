package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.CategoryBreakdownResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.ExpenseSummaryResponse;
import com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary.MonthlySpendingResponse;
import com.example.ExpenseTracker.TestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FinancialInsightsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtility testUtility;
    @Autowired
    private ExpenseRepository repository;
    private String url = "/api/expenses/";

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgre =
            new PostgreSQLContainer<>("postgres:alpine");


    @Test
    void shouldFindExpenseSummaryBetweenTwoDates() throws Exception {
        String userId = "3";
        LocalDate start = LocalDate.of(2024,9,1);
        LocalDate end = LocalDate.of(2024,9,30);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(url +
                "/summary/" + userId + "/" + start + "/" + end))
                .andExpect(status().isOk())
                .andReturn();

        ExpenseSummaryResponse result = testUtility
                .convertMVCResultToObject(mvcResult,
                ExpenseSummaryResponse.class);
        Double expected = testUtility.findSumOfAllExpenses(
                repository,start,end, Long.valueOf(userId));

        assertThat(result.getExpenseTotal())
                .isEqualTo(expected);
    }


    @Test
    void shouldNotFindExpenseSummaryBetweenTwoDates() throws Exception {
        String userId = "3";
        LocalDate end = LocalDate.of(2024,9,1);
        LocalDate start = LocalDate.of(2024,9,30);

        mockMvc.perform(MockMvcRequestBuilders.get(url +
                    "/summary/" + userId + "/" + start + "/" + end))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldFindExpenseTotalsPerCategory() throws Exception {
        String userId = "3";

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(
                url + "/category-breakdown/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        CategoryBreakdownResponse result = testUtility
                .convertMVCResultToObject(mvcResult,
                        CategoryBreakdownResponse.class);

        List<CategoryExpense> categoryExpenses = testUtility
                .categoryExpenseConverter(repository,
                        Long.valueOf(userId));

        CategoryExpense expected = categoryExpenses.get(0);
        CategoryExpense firstItemInList = result
                .getCategoryExpenses().get(0);

        assertThat(firstItemInList.getCategoryId())
                .isEqualTo(expected.getCategoryId());

        assertThat(firstItemInList.getTotalExpense())
                .isEqualTo(expected.getTotalExpense());

        assertThat(result.getCategoryExpenses().size())
                .isEqualTo(categoryExpenses.size());
    }


    @Test
    void shouldFindMonthlyExpenseTotal() throws Exception {
        String userId = "3";
        LocalDate start = LocalDate.of(2024, 5, 1);
        LocalDate end = LocalDate.of(2024, 9, 30);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/trends/monthly/" + userId +
                                "/" + start + "/" + end))
                .andExpect(status().isOk())
                .andReturn();

        List<Month> monthlyExpense = testUtility
                .findMonthlySumOfExpenses(repository,
                        start, end, Long.valueOf(userId));

        MonthlySpendingResponse result = testUtility
                .convertMVCResultToObject(mvcResult,
                        MonthlySpendingResponse.class);

        assertThat(result.getMonths().size())
                .isEqualTo(monthlyExpense.size());
    }


    @Test
    void shouldNotFindMonthlyExpenseTotal() throws Exception {
        String userId = "3";
        LocalDate end = LocalDate.of(2024,9,1);
        LocalDate start = LocalDate.of(2024,9,30);

        mockMvc.perform(MockMvcRequestBuilders.get(url +
                        "/trends/monthly/" + userId + "/" + start +
                        "/" + end))
                .andExpect(status().isBadRequest());
    }
}