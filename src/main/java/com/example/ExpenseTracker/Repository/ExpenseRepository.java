package com.example.ExpenseTracker.Repository;

import org.springframework.data.repository.ListCrudRepository;
import com.example.ExpenseTracker.Model.Expense;
public interface ExpenseRepository extends ListCrudRepository<Expense,Long> {
}
