package com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Model.CategoryExpense;

import java.util.List;

public class CategoryBreakdownResponse {
    private List<CategoryExpense> categoryExpenses;
    private String currency = "Rand";


    public CategoryBreakdownResponse(List<CategoryExpense> categoryExpenses) {
        this.categoryExpenses = categoryExpenses;
    }


    public List<CategoryExpense> getCategoryExpenses() {
        return categoryExpenses;
    }


    public void setCategoryExpenses(List<CategoryExpense> categoryExpenses) {
        this.categoryExpenses = categoryExpenses;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
