package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(value="Expense")
public class Expense {
        @Id
        Long id;
        Double amount;
        LocalDate date;
        String description;
        Long  user_id;
        Long category_id;

        public Expense(Double amount, String description, Long user_id, Long category_id) {
                this.amount = amount;
                this.description = description;
                this.user_id = user_id;
                this.category_id = category_id;
                this.date = LocalDate.now();
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
                return user_id;
        }

        public void setUser(@NotBlank Long user_id) {
                this.user_id = user_id;
        }

        public @NotBlank Long getCategory() {
                return category_id;
        }

        public void setCategory(@NotBlank Long category_id) {
                this.category_id = category_id;
        }
}
