package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Service.FindBy;
import com.example.ExpenseTracker.Service.Save;
import com.example.ExpenseTracker.Service.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageExpenseService implements Save<Expense>, Update<Expense>, FindBy<Expense> {
    private final ExpenseRepository expenseRepository;

    public ManageExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }



    @Override
    public Expense findEntityById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElse(null);
    }

    @Override
    public void save(Expense entity) {
        expenseRepository.save(entity);

    }

    @Override
    public void updateEntity(Long id, Expense entity) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        Expense expense;

        if(expenseOptional.isPresent()){
            expense = expenseOptional.get();
            entity.setId(expense.getId());
            expenseRepository.save(entity);

        } else{

        }

    }
}
