package com.example.ExpenseTracker.Repository;


import org.springframework.data.repository.ListCrudRepository;
import com.example.ExpenseTracker.Model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends ListCrudRepository<Expense,Long> , CustomExpenseRepository{
   List<Expense> findAllByCategoryId(Long categoryId);

}
