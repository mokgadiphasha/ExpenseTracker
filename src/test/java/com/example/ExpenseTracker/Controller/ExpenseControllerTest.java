package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Expense;
import com.example.ExpenseTracker.TestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExpenseControllerTest {
    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  TestUtility testUtility;
    private String url = "/api/expenses";



    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgre =
            new PostgreSQLContainer<>("postgres:alpine");


    @Test
    void shouldSaveExpense() throws Exception {
        String expenseId = "5";
        String userId = "3";
        Expense expected = new Expense(500.00,
                "Bought Flu Medication",
                3L,5L,
                LocalDate.of(2024,9,21));

        String jsonString = testUtility.convertToJSON(expected);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/" + expenseId
                                + "/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        Expense result = testUtility
                .convertMVCResultToObject(mvcResult, Expense.class);
        expected.setId(Long.valueOf(expenseId));


        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(expected.getUser());
        assertThat(result.getId()).isEqualTo(expected.getId());
        assertThat(result.getDate()).isEqualTo(expected.getDate());
        assertThat(result.getAmount()).isEqualTo(expected.getAmount());
        assertThat(result.getCategory()).isEqualTo(expected.getCategory());

        assertThat(result.getDescription())
                .isEqualTo(expected.getDescription());
    }


    @Test
    void shouldFindExpense() throws Exception {
        String expenseId = "5";
        String userId = "3";
        Expense expected = new Expense(500.00,
                "Bought Flu Medication",
                3L,5L,
                LocalDate.of(2024,9,21));
        expected.setId(Long.valueOf(expenseId));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/" + expenseId+ "/"
                                + userId))
                .andExpect(status().isOk())
                .andReturn();

        Expense result = testUtility
                .convertMVCResultToObject(mvcResult, Expense.class);

        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(expected.getUser());
        assertThat(result.getId()).isEqualTo(expected.getId());
        assertThat(result.getDate()).isEqualTo(expected.getDate());
        assertThat(result.getAmount()).isEqualTo(expected.getAmount());

        assertThat(result.getDescription())
                .isEqualTo(expected.getDescription());

        assertThat(result.getCategory()).isEqualTo(expected.getCategory());

    }


    @Test
    void shouldNotFindNonExistentExpense() throws Exception {
        String expenseId = "99";
        String userId = "3";

        mockMvc.perform(get(url + "/" + expenseId
                + "/" + userId))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldGetExpenseByCategory() throws Exception {
        String categoryId = "1";
        String userId = "3";
        Expense expected = new Expense(600.00,
                "had dinner at the food house restaurant",
                3L,1L,LocalDate
                .of(2024,9,20));

        MvcResult mvcResult = mockMvc.perform(get(url + "/filter/"
                        + categoryId + "/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        List<Expense> expenses = testUtility
                .convertMVCResultToList(mvcResult, Expense.class);
        Expense firstExpenseInList = expenses.get(0);

        assertThat(expenses.size()).isEqualTo(2);
        assertThat(firstExpenseInList).isNotNull();

        assertThat(firstExpenseInList.getUser())
                .isEqualTo(expected.getUser());

        assertThat(firstExpenseInList.getDate())
                .isEqualTo(expected.getDate());

        assertThat(firstExpenseInList.getAmount())
                .isEqualTo(expected.getAmount());

        assertThat(firstExpenseInList.getDescription())
                .isEqualTo(expected.getDescription());

        assertThat(firstExpenseInList.getCategory())
                .isEqualTo(expected.getCategory());

    }


    @Test
    void shouldNotFindExpenseWithNonExistentCategoryId() throws Exception {
        String categoryId = "99";
        String userId = "3";

       MvcResult mvcResult = mockMvc.perform(get(url + "/filter/"
                + categoryId + "/" + userId))
                .andExpect(status().isOk())
               .andReturn();

       List<Expense> expenses = testUtility
               .convertMVCResultToList(mvcResult, Expense.class);

       assertThat(expenses.size()).isEqualTo(0);
    }


    @Test
    void shouldGetAllExpensesByUser() throws Exception {
        String userId = "3";
        Expense expected = new Expense(600.00,
                "Went to buy dress for the dinner tomorrow",
                3L,5L,LocalDate
                .of(2024,9,19));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/all/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        List<Expense> expenses = testUtility
                .convertMVCResultToList(mvcResult, Expense.class);
        Expense firstExpenseInList = expenses.get(1);


        assertThat(expenses.size()).isEqualTo(4);
        assertThat(firstExpenseInList).isNotNull();

        assertThat(firstExpenseInList.getUser())
                .isEqualTo(expected.getUser());

        assertThat(firstExpenseInList.getDate())
                .isEqualTo(expected.getDate());

        assertThat(firstExpenseInList.getAmount())
                .isEqualTo(expected.getAmount());

        assertThat(firstExpenseInList.getDescription())
                .isEqualTo(expected.getDescription());

        assertThat(firstExpenseInList.getCategory())
                .isEqualTo(expected.getCategory());
    }

    @Test
    void shouldUpdateExpense() throws Exception {
        String expenseId = "2";
        String userId = "3";
        Expense updatedExpense = new Expense(1200.00,
                "Paid school fees",
                3L,7L,LocalDate
                .of(2024,8,30));

        String jsonString = testUtility.convertToJSON(updatedExpense);

        mockMvc.perform(MockMvcRequestBuilders.put(url + "/" +
                                expenseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/" + expenseId + "/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        Expense result = testUtility.convertMVCResultToObject(mvcResult,
                Expense.class);

        assertThat(result.getAmount()).isEqualTo(updatedExpense
                .getAmount());
    }

    @Test
    void shouldNotUpdateNonExistentExpense() throws Exception {
        String expenseId = "99";


        Expense updatedExpense = new Expense(1200.00,
                "Paid school fees",
                3L,7L,LocalDate
                .of(2024,8,30));

        String jsonString = testUtility.convertToJSON(updatedExpense);

        mockMvc.perform(put(url + "/" + expenseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deleteExpense() throws Exception {
        String expenseId = "1";
        String userId = "3";

        mockMvc.perform(delete(url + "/" + expenseId))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .get(url + "/" + expenseId+ "/"
                    + userId))
                .andExpect(status().isBadRequest());
    }

}