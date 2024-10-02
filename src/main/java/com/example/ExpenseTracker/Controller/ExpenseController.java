package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Service.Expense.BaseExpenseCRUDServiceManager;
import com.example.ExpenseTracker.Service.Expense.ExpenseFilter;
import com.example.ExpenseTracker.Service.Expense.ExpenseServiceManager;
import jakarta.validation.Valid;
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
    public void saveExpense(@Valid @RequestBody Expense expense){
        expenseServiceManager.saveExpense(expense);
    }


    @GetMapping("/{expenseId}/{userId}")
    public Expense getExpense(
            @PathVariable Long expenseId,
            @PathVariable Long userId){
        return expenseFilterManager.findExpenseById(expenseId,userId);
    }


    @PutMapping("/{expenseId}")
    public void UpdateExpense(@Valid @PathVariable Long expenseId, @RequestBody Expense expense){
        expenseServiceManager.updateExpense(expenseId,expense);
    }


    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId){
        expenseServiceManager.deleteExpense(expenseId);
    }


    @GetMapping("/filter/{categoryId}/{userId}")
    public List<Expense> getExpenseByCategory(
            @PathVariable Long categoryId,
            @PathVariable Long userId){
        return expenseFilterManager.findByFilter(categoryId,userId);
    }


    @GetMapping("/all/{userId}")
    public List<Expense> getAllExpensesByUser(@PathVariable Long userId){
        return expenseServiceManager.getAllExpensesByUser(userId);
    }

}
