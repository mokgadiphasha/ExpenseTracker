package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
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
        return user.orElseThrow(()->
                new GlobalExceptionHandler("User with id: "
                        + id+ " does not exist."));
    }


    @Override
    public void saveUser(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if(!userRepository.existsByUsernameAndEmail(username,email)){
            userRepository.save(user);
        } else {
            throw new GlobalExceptionHandler("User already registered.");
        }

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
            throw new GlobalExceptionHandler("An error occurred: User with id: " + id.toString()
            + " could not be updated.");
        }
    }
}
