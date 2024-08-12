package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Category;
import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.example.ExpenseTracker.Service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ManageExpenseService implements Save<Expense>, Update<Expense>, FindBy<Expense> , Delete, FindByFilter<Expense> {
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


    @Override
    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }


    @Override
    public List<Expense> findByFilter(Long id) {
        return expenseRepository.findAllByCategoryId(id);

    }
}
