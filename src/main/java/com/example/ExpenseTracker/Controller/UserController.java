package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.AuthRequest;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Responses.Security.AuthResponse;
import com.example.ExpenseTracker.Service.User.AuthenticateUser;
import com.example.ExpenseTracker.Service.User.BaseUserCRUDServiceManager;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final BaseUserCRUDServiceManager UserServiceManager;
    private final AuthenticateUser authenticateUserManager;

    public UserController(BaseUserCRUDServiceManager userServiceManager,
                          AuthenticateUser authenticateUser) {
        UserServiceManager = userServiceManager;
        authenticateUserManager = authenticateUser;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody User user){
        UserServiceManager.saveUser(user);
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return UserServiceManager.findUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateUser(@Valid @PathVariable Long id, @RequestBody User user){
        UserServiceManager.updateUser(id,user);
    }

    @PostMapping("/login")
    public AuthResponse createAuthentication (@Valid @RequestBody AuthRequest request){
        return authenticateUserManager.createAuthentication(request);
    }


}
