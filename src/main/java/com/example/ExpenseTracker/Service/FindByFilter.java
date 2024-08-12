package com.example.ExpenseTracker.Service;

import java.util.List;

public interface FindByFilter <T>{

     List<T> findByFilter(T filter);
}
