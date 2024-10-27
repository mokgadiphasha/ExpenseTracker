package com.example.ExpenseTracker.Responses.Security;

public class AuthResponse {
    private String generatedJwt;
    private Long userId;

    public AuthResponse(String generatedJwt, Long userId ) {
        this.generatedJwt = generatedJwt;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGeneratedJwt() {
        return generatedJwt;
    }

    public void setGeneratedJwt(String generatedJwt) {
        this.generatedJwt = generatedJwt;
    }



}
