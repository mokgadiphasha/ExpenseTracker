package com.example.ExpenseTracker.Responses.Security;

public class AuthResponse {
    private String generatedJwt;

    public AuthResponse(String generatedJwt) {
        this.generatedJwt = generatedJwt;
    }

    public String getGeneratedJwt() {
        return generatedJwt;
    }

    public void setGeneratedJwt(String generatedJwt) {
        this.generatedJwt = generatedJwt;
    }



}
