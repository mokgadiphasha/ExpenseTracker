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
    public void save(User entity) {
        userRepository.save(entity);
    }


    @Override
    public User findEntityById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);

    }

    @Override
    public void updateEntity(Long id, User entity) {
        Optional<User> userOptional = userRepository.findById(id);
        User user;

        if(userOptional.isPresent()){
            user = userOptional.get();
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            user.setUsername(entity.getUsername());
            userRepository.save(entity);

        } else{

        }

    }
}
