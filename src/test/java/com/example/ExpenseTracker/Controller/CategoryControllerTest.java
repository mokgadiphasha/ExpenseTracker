package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.Category;
import com.example.ExpenseTracker.TestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class,
//        SecurityFilterAutoConfiguration.class
//})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtility testUtility;

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:alpine");

    @Test
    void getCategories() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/category"))
                .andExpect(status().isOk())
                .andReturn();

        List<Category> result =  testUtility
                .convertMVCResultToList(mvcResult, Category.class);

        assertThat(result.size()).isEqualTo(10);

    }
}