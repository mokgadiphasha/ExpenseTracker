package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Service.Expense.BaseExpenseCRUDServiceManager;
import com.example.ExpenseTracker.Service.Expense.ExpenseFilter;
import com.example.ExpenseTracker.Service.Expense.ExpenseServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final BaseExpenseCRUDServiceManager expenseServiceManager;
    private final ExpenseFilter expenseFilterManager;

    public ExpenseController(BaseExpenseCRUDServiceManager expenseServiceManager, ExpenseFilter expenseFilterManager) {
        this.expenseServiceManager = expenseServiceManager;
        this.expenseFilterManager = expenseFilterManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpense(@RequestBody Expense expense){
        expenseServiceManager.saveExpense(expense);
    }


    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable Long id){
        return expenseFilterManager.findExpenseById(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateExpense(@PathVariable Long id, @RequestBody Expense expense){
        expenseServiceManager.updateExpense(id,expense);
    }


    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseServiceManager.deleteExpense(id);
    }


    @GetMapping("/filter/{id}")
    public List<Expense> getExpenseByCategory(@PathVariable Long id){
        return expenseFilterManager.findByFilter(id);
    }


}
