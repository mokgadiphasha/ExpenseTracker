package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.Expense;
import org.junit.jupiter.api.AfterEach;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;



@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository underTest;
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


//    @Test
//    void testDatabaseItems(){
//        assertThat(allBootstrappedExpenses.size())
//                .isEqualTo(underTest.findAll().size());
//    }


    @Test
    void testConnection(){
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @Test
    void shouldFindAllByCategoryIdAndUserId() {
        List<Expense> expected = underTest.findAll()
                .stream().filter(expense -> expense
                        .getCategory().equals(8L))
                .collect(Collectors.toList());

        List<Expense> result = underTest
                .findAllByCategoryIdAndUserId(8L,1L);

        for (int i = 0; i < expected.size(); i++) {
            Expense expectedExpense = expected.get(i);
            Expense resultExpense = result.get(i);

            assertThat(resultExpense.getUser())
                    .isEqualTo(expectedExpense.getUser());

            assertThat(resultExpense.getCategory())
                    .isEqualTo(expectedExpense.getCategory());

            assertThat(resultExpense.getDescription())
                    .isEqualTo(expectedExpense.getDescription());

            assertThat(resultExpense.getDate())
                    .isEqualTo(expectedExpense.getDate());

            assertThat(resultExpense.getAmount())
                    .isEqualTo(expectedExpense.getAmount());

            assertThat(resultExpense.getId())
                    .isEqualTo(expectedExpense.getId());
        }

    }


    @Test
    void shouldNotFindAllByCategoryIdAndUserIdIfCategoryIdInvalid(){
        List<Expense> expected = new ArrayList<>();
        List<Expense> result = underTest
                .findAllByCategoryIdAndUserId(11L,1L);

        assertThat(result.size()).isEqualTo(expected.size());
    }


    @Test
    void shouldFindAllExpensesByUserWithValidUserId() {
        List<Expense> expected = underTest.findAll()
                .stream().filter(expense -> expense.getUser()
                        .equals(1L)).collect(Collectors.toList());

        List<Expense> result = underTest.findAllByUserId(1L);

        assertThat(result.size())
                .isEqualTo(expected.size());

        for (int i = 0; i < expected.size(); i++) {
            Expense expectedExpense = expected.get(i);
            Expense resultExpense = result.get(i);

            assertThat(resultExpense.getUser())
                    .isEqualTo(expectedExpense.getUser());
        }
    }


//    @Test
//    void findByValidExpenseIdAndValidUserId() {
//        Expense expected = underTest.findAll()
//                .stream().filter(expense -> expense.getUser().equals(1L)
//                && expense.getId().equals(1L))
//                .findFirst().get();
//        System.out.println("USER: " + expected.getUser());
//        System.out.println("DESCRIPTION: " + expected.getDescription());
//        System.out.println("DATE: " + expected.getDate());
//        System.out.println("CATEGORY: " + expected.getCategory());
//
//        Optional<Expense> result = underTest
//                .findByIdAndUserId(1L,1L);
//
//        System.out.println("USER: " + result.get().getUser());
//        System.out.println("DESCRIPTION: " + result.get().getDescription());
//        System.out.println("DATE: " + result.get().getDate());
//        System.out.println("CATEGORY: " + result.get().getCategory());
//
//        assertThat(result.isPresent()).isTrue();
//
////        assertThat(result.get().getId())
////                .isEqualTo(expected.getId());
////
////        assertThat(result.get().getUser())
////                .isEqualTo(expected.getUser());
//    }

    @Test
    void shouldNotFindAllByInvalidUserId(){
        List<Expense> expected = new ArrayList<>();
        List<Expense> result = underTest
                .findAllByUserId(99L);

        assertThat(result.size()).isEqualTo(expected.size());
    }


    @Test
    void findByInvalidExpenseIdAndValidUserId(){
        Optional<Expense> result = underTest
                .findByIdAndUserId(1L,1L);

        assertThat(result.isPresent()).isFalse();
    }

}