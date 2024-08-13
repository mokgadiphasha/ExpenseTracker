package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Service.Expense.ExpenseServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseServiceManager expenseServiceManager;

    public ExpenseController(ExpenseServiceManager expenseServiceManager) {
        this.expenseServiceManager = expenseServiceManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody Expense expense){
        expenseServiceManager.saveExpense(expense);
    }


    @GetMapping("/{id}")
    public Expense getUser(@PathVariable Long id){
        return expenseServiceManager.findExpenseById(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateUser(@PathVariable Long id, @RequestBody Expense expense){
        expenseServiceManager.updateExpense(id,expense);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        expenseServiceManager.deleteExpense(id);
    }


    @GetMapping("/filter/{id}")
    public List<Expense> getUserByCategory(@PathVariable Long id){
        return expenseServiceManager.findByFilter(id);
    }


}
