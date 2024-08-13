package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Category;
import com.example.ExpenseTracker.Service.Category.BaseCategoryCRUDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final BaseCategoryCRUDService categoryServiceManager;

    public CategoryController(BaseCategoryCRUDService categoryServiceManager) {
        this.categoryServiceManager = categoryServiceManager;
    }

    @GetMapping("")
    public List<Category> getCategories(){
        return categoryServiceManager.findAllCategories();
    }
}
