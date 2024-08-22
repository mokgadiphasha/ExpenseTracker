package com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary;

import com.example.ExpenseTracker.Model.Month;

import java.util.List;

public class MonthlySpendingResponse {
    List<Month> months;
    String currency = "Rand";

    public MonthlySpendingResponse(List<Month> months) {
        this.months = months;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
