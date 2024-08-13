package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Model.User;

public interface BaseUserCRUDServiceManager {
    User findUserById(Long id);
    void saveUser(User user);
    void updateUser(Long id, User user);
}
