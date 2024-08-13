package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Service.User.BaseUserCRUDServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final BaseUserCRUDServiceManager UserServiceManager;

    public UserController(BaseUserCRUDServiceManager userServiceManager) {
        UserServiceManager = userServiceManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        UserServiceManager.saveUser(user);
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return UserServiceManager.findUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateUser(@PathVariable Long id, @RequestBody User user){
        UserServiceManager.updateUser(id,user);
    }



}
