package com.example.ExpenseTracker.Repository;

import com.example.ExpenseTracker.Model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User,Long> {
    boolean existsByUsernameAndEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
