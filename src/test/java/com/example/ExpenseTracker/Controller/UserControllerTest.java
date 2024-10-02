package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.TestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserControllerTest {
    String url = "/api/users";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtility testUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new
            PostgreSQLContainer<>("postgres:alpine");

    @Test
    void shouldSaveUser() throws Exception {
        String userId = "4";
        User expected = new User("Melrose",
                "Mel246","Mel@email.com");


        String jsonString = testUtility.convertToJSON(expected);

        mockMvc.perform(MockMvcRequestBuilders.post(url +
                                "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(
        url + "/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        User result = testUtility
                .convertMVCResultToObject(mvcResult, User.class);



        assertThat(result.getEmail())
                .isEqualTo(expected.getEmail());
        assertThat(passwordEncoder.matches(
                expected.getPassword(),result
                        .getPassword())).isTrue();

        assertThat(result.getUsername())
                .isEqualTo(expected.getUsername());
    }


    @Test
    void shouldFindUser() throws Exception {
        String userId = "3";
        User expected = new User("lilly",
                "lilly123","lilly@email.com");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(url +
                        "/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        User result = testUtility
                .convertMVCResultToObject(mvcResult
                        , User.class);

        assertThat(result.getUsername())
                .isEqualTo(expected.getUsername());

        assertThat(result.getEmail())
                .isEqualTo(expected.getEmail());

        assertThat(result.getPassword())
                .isEqualTo(expected.getPassword());

    }


    @Test
    void shouldNotFindNonExistentUser() throws Exception {
        String userId = "99";
        mockMvc.perform(
                        MockMvcRequestBuilders.get(url +
                                "/" + userId))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    void shouldUpdateUser() throws Exception {
        String userId = "3";
        User expected = new User("lillyPie",
                "lilly123","lilly@email.com");

        String jsonString = testUtility.convertToJSON(expected);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(url + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(
                        url + "/" + userId))
                .andExpect(status().isOk()).andReturn();


        User result = testUtility
                .convertMVCResultToObject(mvcResult
                        , User.class);

        assertThat(result.getUsername())
                .isEqualTo(expected.getUsername());
    }


    @Test
    void shouldNotUpdateNonExistentUser() throws Exception {
        String userId = "99";
        User expected = new User("lillyPie",
                "lilly123","lilly@email.com");

        String jsonString = testUtility.convertToJSON(expected);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(url + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}