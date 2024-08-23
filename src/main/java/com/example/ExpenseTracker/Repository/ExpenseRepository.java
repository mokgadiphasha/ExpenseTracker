package com.example.ExpenseTracker.Repository;


import org.springframework.data.repository.ListCrudRepository;
import com.example.ExpenseTracker.Model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends ListCrudRepository<Expense,Long> , CustomExpenseRepository{
   List<Expense> findAllByCategoryIdAndUserId(Long categoryId,Long UserId);
   List<Expense> findAllByUserId(Long userId);
   Optional<Expense> findByIdAndUserId(Long expenseId, Long userId);

}
