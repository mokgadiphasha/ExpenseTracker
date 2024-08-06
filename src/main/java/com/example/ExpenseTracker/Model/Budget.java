package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record Budget(
        @Id
        Long id,
        @NotBlank
        User user,
        @NotBlank
        Category category,
        @NotBlank
        Double amount


) {
}
