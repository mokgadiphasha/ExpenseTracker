package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Service.User.ManageUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ManageUserService manageUserService;

    public UserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody User user){
        manageUserService.save(user);
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return manageUserService.findEntityById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateUser(@PathVariable Long id, @RequestBody User user){
        manageUserService.updateEntity(id,user);
    }



}
