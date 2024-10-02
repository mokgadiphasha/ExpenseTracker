package com.example.ExpenseTracker;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.Model.Month;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import com.fasterxml.jackson.databind.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TestUtility {

    @Autowired
    private ObjectMapper objectMapper;


    public List<CategoryExpense>
    categoryExpenseConverter(ExpenseRepository repository,Long userId){
        List<CategoryExpense> categoryExpenseList = new ArrayList<>();

        Map<Long, Double> expenses = repository.findAll()
                .stream()
                .filter(expense -> expense.getUserId().equals(userId))
                .collect(Collectors.groupingBy(
                        Expense::getCategoryId,Collectors
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
                .filter(expense -> expense.getUserId().equals(userId) &&
                       expense.getDate().isAfter(start)
                        && expense.getDate().isBefore(end))
                .mapToDouble(Expense::getAmount)
                .sum();
    }


    public List<Month> findMonthlySumOfExpenses(ExpenseRepository repository,
                                         LocalDate start,LocalDate end,Long userId){
        List<Month> monthlyExpenses = new ArrayList<>();

        Map<YearMonth,Double> expenses = repository.findAll()
                .stream()
                .filter(expense -> expense.getUserId().equals(userId) &&
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


    public <T> List<T> convertMVCResultToList(MvcResult mvcResult,
                                              Class<T> clazz){

        try{
            String jsonResponse = mvcResult.getResponse()
                    .getContentAsString();
            JavaType type = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return objectMapper.readValue(jsonResponse,type);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> String convertToJSON(T objectToSerialize){

        try{
            return objectMapper.writeValueAsString(objectToSerialize);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public <T> T convertMVCResultToObject(MvcResult mvcResult,
                                          Class<T> clazz){

        try{
            String jsonResponse = mvcResult.getResponse()
                    .getContentAsString();
            return objectMapper.readValue(jsonResponse,
                    clazz);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
