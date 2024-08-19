package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(value="Expense")
public class Expense {
        @Id
        private Long id;
        private Double amount;
        private LocalDate date;
        private String description;
        private Long userId;
        private Long categoryId ;

        public Expense(Double amount, String description, Long userId,
                       Long categoryId,LocalDate date) {
                this.amount = amount;
                this.description = description;
                this.userId = userId;
                this.categoryId = categoryId;
                this.date = date;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public @NotBlank Double getAmount() {
                return amount;
        }

        public void setAmount(@NotBlank Double amount) {
                this.amount = amount;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public @NotBlank Long getUser() {
                return userId;
        }

        public void setUser(@NotBlank Long user_id) {
                this.userId = user_id;
        }

        public @NotBlank Long getCategory() {
                return categoryId;
        }

        public void setCategory(@NotBlank Long category_id) {
                this.categoryId = category_id;
        }
}
