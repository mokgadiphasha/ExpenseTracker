package com.example.ExpenseTracker.Model;

import org.springframework.data.relational.core.mapping.Table;

@Table(value = "Category")
public class Category {
    private String name;
    private Long id;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
