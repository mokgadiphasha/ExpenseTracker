package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


public record Expense(
        @Id
        Long id,
        @NotBlank
        Double amount,
        @NotBlank
        LocalDate date,
        String description,
        @NotBlank
        User user,
        @NotBlank
        Catergory catergory

) {
}
