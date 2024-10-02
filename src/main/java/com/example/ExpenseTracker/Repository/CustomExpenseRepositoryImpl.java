package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.CategoryExpense;
import com.example.ExpenseTracker.Model.Month;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomExpenseRepositoryImpl implements  CustomExpenseRepository{
    private final JdbcTemplate jdbcTemplate;

    public CustomExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<CategoryExpense> categoryBreakdownQuery(Long userId){

        String sql = "SELECT user_id, category_id, SUM(amount) AS total_amount " +
                "FROM \"Expense\"" +
                "WHERE user_id = ? " +
                "GROUP BY user_id, category_id " +
                "ORDER BY category_id;";

            return jdbcTemplate.query(sql,new Object[]{userId},
                    new RowMapper<CategoryExpense>(){

                @Override
                public CategoryExpense mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CategoryExpense expense = new CategoryExpense();
                    expense.setCategoryId(rs.getLong("category_id"));
                    expense.setTotalExpense(rs.getDouble("total_amount"));
                    return expense;

                }
            });



    }


    @Override
    public Double summaryBetweenTwoGivenDatesQuery(LocalDate start, LocalDate end, Long userId) {

        String sql = "SELECT SUM(amount) AS total_amount FROM \"Expense\" " +
                "WHERE date BETWEEN ? AND ? " +
                "AND user_id = ?;";


        return jdbcTemplate.queryForObject(sql,new Object[]{start,end,userId}, Double.class);

    }


    @Override
    public List<Month> monthlySpendingSummaryQuery(LocalDate start, LocalDate end, Long userId) {

        String sql = "SELECT EXTRACT(YEAR FROM date) AS year, " +
                "EXTRACT(MONTH FROM date) AS month, " +
                "SUM(amount) AS total " +
                "FROM \"Expense\" " +
                "WHERE user_id = ? " +
                "AND date BETWEEN ? AND ? " +
                "GROUP BY year," +
                "month " +
                "ORDER BY " +
                "year, month;";

        return jdbcTemplate.query(sql, new Object[]{userId, start, end}, new RowMapper<Month>() {
            @Override
            public Month mapRow(ResultSet rs, int rowNum) throws SQLException {
                Month month = new Month();
                month.setTotalExpense(rs.getDouble("total"));
                month.setMonth(rs.getInt("month"));
                month.setYear(rs.getInt("year"));
                return month;
            }
        });
    }
}
