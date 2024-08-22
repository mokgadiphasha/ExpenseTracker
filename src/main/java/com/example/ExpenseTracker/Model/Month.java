package com.example.ExpenseTracker.Model;

import java.time.LocalDate;

public class Month {
    private Double totalExpense;
    private int year;
    private int month;

    public Double getTotalExpense() {
        return totalExpense;
    }


    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
