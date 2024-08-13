package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.UserRepository;
import com.example.ExpenseTracker.Service.FindBy;
import com.example.ExpenseTracker.Service.Save;
import com.example.ExpenseTracker.Service.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageUserService implements Save<User> , FindBy<User>, Update<User> {
    private final UserRepository userRepository;


    public ManageUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveExpense(User expense) {
        userRepository.save(expense);
    }


    @Override
    public User findExpenseById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);

    }

    @Override
    public void updateExpense(Long id, User expense) {
        Optional<User> userOptional = userRepository.findById(id);
        User user;

        if(userOptional.isPresent()){
            user = userOptional.get();
            user.setEmail(expense.getEmail());
            user.setPassword(expense.getPassword());
            user.setUsername(expense.getUsername());
            userRepository.save(user);

        } else{

        }

    }
}
