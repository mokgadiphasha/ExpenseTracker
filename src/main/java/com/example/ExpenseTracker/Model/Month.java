package com.example.ExpenseTracker.Model;

import java.time.LocalDate;

public class Month {
    private LocalDate month;
    private Double totalExpense;

    public Month(LocalDate month, Double totalExpense) {
        this.month = month;
        this.totalExpense = totalExpense;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }
}
