package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.CategoryExpense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomExpenseRepositoryImpl implements  CustomExpenseRepository{
    private final JdbcTemplate jdbcTemplate;

    public CustomExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CategoryExpense> categoryBreakdownQuery(Long userId){

        String sql = "SELECT user_id,category_id,SUM(amount) FROM Expense" +
                    " as total_amount "+
                    "WHERE user_id = ? " +
                    "GROUP BY category_id;";

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
    public Double sumAmountByDateBetweenAndUserId(LocalDate start, LocalDate end, Long userId) {
        String sql = "SELECT SUM(amount) FROM Expense " +
                "WHERE date Between ? AND ? " +
                "AND user_id = ?";

        return jdbcTemplate.queryForObject(sql,new Object[]{start,end,userId}, Double.class);

    }
}
