package com.example.ExpenseTracker.Responses.Financial_Insights.Expense_Summary;

public class ExpenseSummaryResponse {
    private Double expenseTotal;
    private String currency = "Rand";


    public ExpenseSummaryResponse(Double expenseTotal) {
        this.expenseTotal = expenseTotal;
    }


    public Double getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(Double expenseTotal) {
        this.expenseTotal = expenseTotal;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
