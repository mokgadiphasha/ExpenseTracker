package com.example.ExpenseTracker.Service.Category;

import com.example.ExpenseTracker.Model.Category;
import com.example.ExpenseTracker.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceManager implements BaseCategoryCRUDService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
