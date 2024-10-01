package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Model.AuthRequest;
import com.example.ExpenseTracker.Responses.Security.AuthResponse;

public interface AuthenticateUser {
    AuthResponse createAuthentication(AuthRequest authRequest);
}
