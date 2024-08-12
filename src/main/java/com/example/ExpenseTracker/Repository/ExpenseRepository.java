package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.Category;
import org.springframework.data.repository.ListCrudRepository;
import com.example.ExpenseTracker.Model.Expense;

import java.util.List;

public interface ExpenseRepository extends ListCrudRepository<Expense,Long> {
    List<Expense> findByCategory(Category category);
}
