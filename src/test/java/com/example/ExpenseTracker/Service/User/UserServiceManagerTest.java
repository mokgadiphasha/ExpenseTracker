package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class UserServiceManagerTest {

    @Mock
    private  UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceManager serviceManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldFindUserById() {
        User user = new User("John",
                "John1234","johnDoe@email.com");

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        User result = serviceManager.findUserById(1L);

        verify(repository,times(1)).findById(1L);

        assertEquals(user.getEmail(),result.getEmail());
        assertEquals(user.getUsername(),result.getUsername());
        assertEquals(user.getPassword(),result.getPassword());

    }


    @Test
    void shouldNotFindUserById(){
        User user = new User("John",
                "John1234","johnDoe@email.com");

       Exception exception = assertThrows(Exception.class,
               ()-> serviceManager.findUserById(2L));

       String message = "User with id: "
                + 2 + " does not exist.";
       assertEquals(message,exception.getMessage());

        verify(repository,times(1)).findById(2L);


    }


    @Test
    void shouldSaveUser() {
        User user = new User("John",
                "John1234","johnDoe@email.com");
        String username = user.getUsername();
        String email = user.getEmail();

        when(repository.existsByUsernameAndEmail(username,email))
                .thenReturn(false);

        serviceManager.saveUser(user);

        verify(repository,times(1))
                .save(user);
    }


    @Test
    void shouldNotSaveUser(){
        User user = new User("John",
                "John1234","johnDoe@email.com");
        String username = user.getUsername();
        String email = user.getEmail();

        when(repository.existsByUsernameAndEmail(username,email))
                .thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class,
                ()-> serviceManager.saveUser(user));

        String message = "User already registered.";
        assertEquals(message,exception.getMessage());

        verify(repository,times(1))
                .existsByUsernameAndEmail(username,email);
    }


    @Test
    void shouldUpdateUser() {
        User oldUserDetails = new User("John",
                "John1234","johnDoe@email.com");
        User updatedUserDetails = new User("JohnDoe2",
                "John1234","johnD@email.com");

        when(repository.findById(1L)).thenReturn(Optional
                .of(oldUserDetails));

        serviceManager.updateUser(1L,updatedUserDetails);

        verify(repository,times(1))
                .save(updatedUserDetails);
    }


    @Test
    void shouldNotUpdateUser(){
        User user = new User("John",
                "John1234","johnDoe@email.com");

        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class,
                () -> serviceManager.updateUser(1L,user));

        String message = "An error occurred: " +
                "User with id: " + 1
                + " could not be updated.";
        assertEquals(message,exception.getMessage());

        verify(repository,times(1)).
        findById(1L);

    }
}