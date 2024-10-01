package com.example.ExpenseTracker.Security;

import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsManagerService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsManagerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException
                        ("Invalid/Incorrect Username."));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}
