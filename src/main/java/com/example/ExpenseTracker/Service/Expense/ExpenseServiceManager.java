package com.example.ExpenseTracker.Service.Expense;

import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
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
    public Expense findExpenseById(Long id,Long userId) {
        Optional<Expense> expense = expenseRepository.findByIdAndUserId(id,userId);
        return expense.orElseThrow(() -> new GlobalExceptionHandler(
                "An error occurred: Expense with id: " + id.toString()
                + " or user with id: " +userId.toString()));
    }


    @Override
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }


    @Override
    public void updateExpense(Long id, Expense expense) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        Expense oldExpense;

        if(expenseOptional.isPresent()){
            oldExpense = expenseOptional.get();
            expense.setId(oldExpense.getId());
            expenseRepository.save(expense);

        } else{
            throw new GlobalExceptionHandler("An error occurred: Expense with id: "
            + id +" could not be updated.");

        }
    }


    @Override
    public List<Expense> findByFilter(Long categoryId,Long userId) {
        return expenseRepository.findAllByCategoryIdAndUserId(categoryId,userId);
    }


    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> getAllExpensesByUser(Long userId) {
        return expenseRepository.findAllByUserId(userId);
    }
}
