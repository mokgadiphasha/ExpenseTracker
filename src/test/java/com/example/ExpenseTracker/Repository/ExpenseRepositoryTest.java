package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.Expense;
import org.junit.jupiter.api.BeforeAll;

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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExpenseRepositoryTest {
    @Autowired
    private static ExpenseRepository underTest;
    private static List<Expense> allBootstrappedExpenses;

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:alpine");

    @BeforeEach
    void setUp() {
        Expense expenseOne = new Expense(100.0,
                "Purchase for new shoes",
                1L,8L,LocalDate.now());
        Expense expenseTwo = new Expense(500.0,
                "Purchase for new dress",
                1L,8L,LocalDate.now());
        Expense expenseThree = new Expense(100.0,
                "Paid for electricity",
                1L,3L,LocalDate.now());
        Expense expenseFour = new Expense(900.0,
                "Paid for annual medical tests",
                1L,5L,
                LocalDate.of(2024,5,20));
        Expense expenseFive = new Expense(200.0,
                "Paid school fees",1L,7L,
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

    @Test
    void testConnection(){
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @Test
    void shouldFindAllByCategoryIdAndUserId() {
        List<Expense> expected = new ArrayList<>();
        Expense expenseOne = new Expense(100.0,
                "Purchase for new shoes",
                1L,8L,LocalDate.now());
        Expense expenseTwo = new Expense(500.0,
                "Purchase for new dress",
                1L,8L,LocalDate.now());

        expected.add(expenseOne);
        expected.add(expenseTwo);

        List<Expense> result = underTest
                .findAllByCategoryIdAndUserId(8L,1L);

        assertThat(result).containsExactlyElementsOf(expected)
                .isEqualTo(expected.size());

    }


    @Test
    void shouldNotFindAllByCategoryIdAndUserIdIfCategoryIdInvalid(){
        List<Expense> expected = new ArrayList<>();
        List<Expense> result = underTest
                .findAllByCategoryIdAndUserId(11L,1L);

        assertThat(result.size()).isEqualTo(expected.size());
    }


    @Test
    void shouldFindAllByValidUserId() {
        List<Expense> expected = allBootstrappedExpenses;
        List<Expense> result = underTest.findAllByUserId(1L);

        assertThat(result).containsExactlyElementsOf(expected)
                .isEqualTo(expected.size());
    }


    @Test
    void shouldNotFindAllByInvalidUserId(){
        List<Expense> expected = new ArrayList<>();
        List<Expense> result = underTest
                .findAllByUserId(99L);

        assertThat(result.size()).isEqualTo(expected.size());
    }


    @Test
    void findByValidExpenseIdAndValidUserId() {
        Expense expected = new Expense(100.0,
                "Purchase for new shoes",
                1L,8L,LocalDate.now());

        Optional<Expense> result = underTest
                .findByIdAndUserId(1L,1L);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);
    }


    @Test
    void findByInvalidExpenseIdAndValidUserId(){
        Optional<Expense> result = underTest
                .findByIdAndUserId(1L,1L);

        assertThat(result.isPresent()).isFalse();
    }
}