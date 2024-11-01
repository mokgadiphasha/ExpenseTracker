package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.Category;
import com.example.ExpenseTracker.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:alpine");


    @Test
    void shouldExistByUsernameAndEmail() {
        User user = underTest.findById(1L).get();
        String username = user.getUsername();
        String email = user.getEmail();

        boolean result = underTest
                .existsByUsernameAndEmail(username,email);

        assertThat(result)
                .isTrue();
    }


    @Test
    void shouldNotExistByUsernameAndEmail(){
        String username = "JaneD";
        String email = "JaneD@gmail.com";
        boolean result = underTest
                .existsByUsernameAndEmail(username,email);

        assertThat(result)
                .isFalse();
    }
}