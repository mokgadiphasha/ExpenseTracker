package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceManagerTest {

    @Mock
    private  ExpenseRepository repository;
    @InjectMocks
    private ExpenseServiceManager serviceManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindExpenseById() {
        Expense expense = new Expense(100.00,"rent money.",
                1L,1L, LocalDate.now());

        when(repository.findByIdAndUserId(1L,1L))
                .thenReturn(Optional.of(expense));

        Expense result = serviceManager.findExpenseById(1L,1L);

        assertEquals(expense.getAmount(),result.getAmount());
        assertEquals(expense.getId(),result.getId());
        assertEquals(expense.getCategory(),result.getCategory());
        assertEquals(expense.getDate(),result.getDate());
        assertEquals(expense.getUser(),result.getUser());
        assertEquals(expense.getDescription(),result.getDescription());

        verify(repository,times(1))
                .findByIdAndUserId(1L,1L);
    }


    @Test
    void shouldNotFindUserById(){
        when(repository.findByIdAndUserId(1L,1L))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class,
                () -> serviceManager.findExpenseById(1L,1L));

        String message = "An error occurred: Expense with id: " + 1
                + " or user with id: " + 1;
        assertEquals(message,exception.getMessage());

        verify(repository,times(1))
                .findByIdAndUserId(1L,1L);
    }

    @Test
    void shouldSaveExpense() {
        Expense expense = new Expense(100.00,"rent money.",
                1L,1L, LocalDate.now());

        serviceManager.saveExpense(expense);

        verify(repository,times(1)).save(expense);
    }


    @Test
    void shouldUpdateExpense() {
        Expense expense = new Expense(100.00,"rent money.",
                1L,1L, LocalDate.now());

        when(repository.findById(1L)).thenReturn(Optional.of(expense));

        serviceManager.updateExpense(1L,expense);

        verify(repository,times(1)).findById(1L);
        verify(repository,times(1)).save(expense);
    }


    @Test
    void shouldNotUpdateExpense(){
        Expense expense = new Expense(100.00,"rent money.",
                1L,1L, LocalDate.now());

        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class,
                () -> serviceManager.updateExpense(1L,expense));

        String message = "An error occurred: Expense with id: "
                + 1 +" could not be updated.";

        assertEquals(message,exception.getMessage());
        verify(repository,times(1)).findById(1L);

    }

    @Test
    void shouldFindByFilter() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(100.00,"grocery purchase for myself.",
                1L,3L, LocalDate.now()));
        expenses.add(new Expense(500.00,"grocery purchase for mom.",
                1L,3L, LocalDate.now()));

        when(repository.findAllByCategoryIdAndUserId(3L,1L))
                .thenReturn(expenses);

        List<Expense> result = serviceManager.findByFilter(3L,1L);

        assertEquals(expenses.size(),result.size());

        for (int i = 0; i < expenses.size(); i++) {
            assertEquals(expenses.get(i).getCategory(),
                    result.get(i).getCategory());
        }

        verify(repository,times(1)
        ).findAllByCategoryIdAndUserId(3L,1L);

    }

    @Test
    void shouldDeleteExpense() {
        when(repository.existsById(1L)).thenReturn(true);

        serviceManager.deleteExpense(1L);

        verify(repository,times(1)).existsById(1L);
        verify(repository,times(1)).deleteById(1L);

    }


    @Test
    void shouldNotDeleteExpense(){
        when(repository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class,
                () -> serviceManager.deleteExpense(1L));

        String message = "An error occurred: Expense with id: "
                + 1 +" could not be deleted.";

        assertEquals(message,exception.getMessage());

        verify(repository,times(1)).existsById(1L);
    }

    @Test
    void shouldGetAllExpensesByUser() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(100.00,"grocery purchase for myself.",
                1L,3L, LocalDate.now()));
        expenses.add(new Expense(500.00,"grocery purchase for mom.",
                1L,3L, LocalDate.now()));

        when(repository.findAllByUserId(1L)).thenReturn(expenses);

        List<Expense> result = serviceManager.getAllExpensesByUser(1L);

        for (int i = 0; i < expenses.size(); i++) {
            assertEquals(expenses.get(i).getUser(),
                    result.get(i).getUser());
        }

        verify(repository,times(1)).findAllByUserId(1L);


    }
}