package com.example.ExpenseTracker;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TestUtility {

    public List<CategoryExpense> categoryExpenseConverter(ExpenseRepository repository,Long userId){
        List<CategoryExpense> categoryExpenseList = new ArrayList<>();

        Map<Long, Double> expenses = repository.findAll()
                .stream()
                .filter(expense -> expense.getUser().equals(userId))
                .collect(Collectors.groupingBy(
                        Expense::getCategory,Collectors
                                .summingDouble(Expense::getAmount)));

        for (Map.Entry<Long,Double> expense: expenses.entrySet()){

            CategoryExpense categoryExpense = new CategoryExpense();
            categoryExpense.setTotalExpense(expense.getValue());
            categoryExpense.setCategoryId(expense.getKey());

            categoryExpenseList.add(categoryExpense);
        }

        return categoryExpenseList;
    }


    public Double findSumOfAllExpenses(ExpenseRepository repository,
                                       LocalDate start,LocalDate end,Long userId){
        return repository.findAll()
                .stream()
                .filter(expense -> expense.getUser().equals(userId) &&
                        expense.getDate().isEqual(start) ||
                        expense.getDate().isAfter(start) &&
                                expense.getDate().isBefore(end) ||
                        expense.getDate().isEqual(end))
                .mapToDouble(Expense::getAmount)
                .sum();
    }


    public List<Month> findMonthlySumOfExpenses(ExpenseRepository repository,
                                         LocalDate start,LocalDate end,Long userId){
        List<Month> monthlyExpenses = new ArrayList<>();

        Map<YearMonth,Double> expenses = repository.findAll()
                .stream()
                .filter(expense -> expense.getUser().equals(userId) &&
                        expense.getDate().isEqual(start) ||
                        expense.getDate().isAfter(start) &&
                                expense.getDate().isBefore(end) ||
                        expense.getDate().isEqual(end))
                .collect(Collectors.groupingBy(expense -> YearMonth
                        .from(expense.getDate()),Collectors
                        .summingDouble(Expense::getAmount)));

        for (Map.Entry<YearMonth,Double> expense : expenses.entrySet()){

            Month month = new Month();
            month.setTotalExpense(expense.getValue());
            month.setMonth(expense.getKey().getMonthValue());
            month.setYear(expense.getKey().getYear());

            monthlyExpenses.add(month);
        }

        return monthlyExpenses;

    }
}
