package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(value="Expense")
public class Expense {
        @Id
        private Long id;
        @NotNull
        private Double amount;
        @NotNull
        private LocalDate date;
        @NotBlank
        private String description;
        @NotNull
        private Long userId;
        @NotNull
        private Long categoryId ;

        public Expense(Double amount, String description, Long userId,
                       Long categoryId,LocalDate date) {
                this.amount = amount;
                this.description = description;
                this.userId = userId;
                this.categoryId = categoryId;
                this.date = date;
        }

        public Expense(){}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Double getAmount() {
                return amount;
        }

        public void setAmount(Double amount) {
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

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long user_id) {
                this.userId = user_id;
        }

        public Long getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(Long category_id) {
                this.categoryId = category_id;
        }

}
