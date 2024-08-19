package com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Model.Category;

import java.util.List;

public class CategoryBreakdownResponse {
    private List<Category> categoryExpenses;
    private String currency = "Rand";


    public CategoryBreakdownResponse(List<Category> categoryExpenses) {
        this.categoryExpenses = categoryExpenses;
    }


    public List<Category> getCategoryExpenses() {
        return categoryExpenses;
    }


    public void setCategoryExpenses(List<Category> categoryExpenses) {
        this.categoryExpenses = categoryExpenses;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
