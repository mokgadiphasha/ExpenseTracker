package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.TestUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository underTest;
    private final TestUtility testUtility = new TestUtility();
    private static List<Expense> allBootstrappedExpenses;


    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:alpine");


    @BeforeEach
    void setUp() {
        Expense expenseOne = new Expense(100.0,
                "Purchase for new shoes",
                1L,1L,
                LocalDate.of(2024,8,1));
        Expense expenseTwo = new Expense(100.0,
                "Purchase for new dress",
                1L,2L,LocalDate.now());
        Expense expenseThree = new Expense(100.0,
                "Paid for electricity",
                1L,3L,LocalDate.now());
        Expense expenseFour = new Expense(100.0,
                "Paid for annual medical tests",
                1L,4L,
                LocalDate.of(2024,5,20));
        Expense expenseFive = new Expense(200.0,
                "Paid school fees",2L,7L,
                LocalDate.of(2024,8,30));

        allBootstrappedExpenses = new ArrayList<>();

        allBootstrappedExpenses.add(expenseOne);
        allBootstrappedExpenses.add(expenseTwo);
        allBootstrappedExpenses.add(expenseThree);
        allBootstrappedExpenses.add(expenseFour);
        allBootstrappedExpenses.add(expenseFive);

        for (Expense expense: allBootstrappedExpenses){
            underTest.save(expense);
        }

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();

    }

    @Test
    void testConnection(){
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @Test
    void testDatabase(){
        assertThat(underTest.findAll().size())
                .isEqualTo(allBootstrappedExpenses.size());
    }

    @Test
    void shouldReturnExpensesForEachCategoryForUser() {
        List<CategoryExpense> expected =
                testUtility.categoryExpenseConverter(underTest,1L);

        List<CategoryExpense> result = underTest
                .categoryBreakdownQuery(1L);

        assertThat(result.size()).isEqualTo(expected.size());

        for (int i = 0; i < expected.size(); i++) {
            CategoryExpense expectedExpense = expected.get(i);
            CategoryExpense resultExpense = result.get(i);

            assertThat(resultExpense.getTotalExpense())
                    .isEqualTo(expectedExpense.getTotalExpense());

            assertThat(resultExpense.getCategoryId())
                    .isEqualTo(expectedExpense.getCategoryId());

        }
    }

    @Test
    void shouldSumAmountUsingTwoDatesAndUserId() {
        LocalDate start = LocalDate.of(2024,5,1);
        LocalDate end = LocalDate.of(2024,8,30);

        Double expected = testUtility
                .findSumOfAllExpenses(underTest,start,end,1L);

        Double result = underTest
                .sumAmountByDateBetweenAndUserId(start,end,1L);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldSumAmountPerMonthForUser() {
        LocalDate start = LocalDate.of(2024,1,1);
        LocalDate end = LocalDate.of(2024,8,30);

        List<Month>  expected = testUtility
                .findMonthlySumOfExpenses(underTest,start,end,1L);

        List<Month> result = underTest
                .sumAmountByMonthBetweenAndUserId(start,end,1L);

        assertThat(result.size())
                .isEqualTo(expected.size());

    }


}