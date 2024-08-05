package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


public record User(

        @Id
        Long id,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email
) {
}
