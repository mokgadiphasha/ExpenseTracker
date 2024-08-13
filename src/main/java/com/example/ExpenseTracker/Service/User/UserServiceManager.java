package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceManager implements BaseUserCRUDServiceManager{
    private final UserRepository userRepository;

    public UserServiceManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Override
    public void updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        User oldUser;

        if(userOptional.isPresent()){
            oldUser = userOptional.get();
            user.setId(oldUser.getId());
            userRepository.save(user);

        } else{

        }
    }
}
