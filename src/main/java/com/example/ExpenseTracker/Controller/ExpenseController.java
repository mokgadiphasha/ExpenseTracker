package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Service.Expense.ManageExpenseService;
import com.example.ExpenseTracker.Service.User.ManageUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ManageExpenseService manageExpenseService;

    public ExpenseController(ManageExpenseService manageExpenseService) {
        this.manageExpenseService = manageExpenseService;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody Expense expense){
        manageExpenseService.save(expense);
    }


    @GetMapping("/{id}")
    public Expense getUser(@PathVariable Long id){
        return manageExpenseService.findEntityById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateUser(@PathVariable Long id, @RequestBody Expense expense){
        manageExpenseService.updateEntity(id,expense);
    }


}
