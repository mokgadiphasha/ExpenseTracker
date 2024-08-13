package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

}
