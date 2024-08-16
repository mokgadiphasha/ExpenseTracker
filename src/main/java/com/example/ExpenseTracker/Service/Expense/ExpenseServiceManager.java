package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceManager implements BaseExpenseCRUDServiceManager , ExpenseFilter{
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceManager(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public Expense findExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElse(null);
    }


    @Override
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }


    @Override
    public void updateExpense(Long id, Expense expense) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        Expense oldExpense;
//        expenseOptional.ifPresent(expense ->);

        if(expenseOptional.isPresent()){
            oldExpense = expenseOptional.get();
            expense.setId(oldExpense.getId());
            expenseRepository.save(expense);

        } else{

        }
    }


    @Override
    public List<Expense> findByFilter(Long id) {
        return expenseRepository.findAllByCategoryId(id);
    }


    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
