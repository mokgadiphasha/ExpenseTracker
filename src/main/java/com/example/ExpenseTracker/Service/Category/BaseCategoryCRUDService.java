package com.example.ExpenseTracker.Service.Category;

import com.example.ExpenseTracker.Model.Category;

import java.util.List;

public interface BaseCategoryCRUDService {
    List<Category> findAllCategories();
}
