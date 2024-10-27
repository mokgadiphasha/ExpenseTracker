package com.example.ExpenseTracker.Service.User;

import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
import com.example.ExpenseTracker.Model.AuthRequest;
import com.example.ExpenseTracker.Model.User;
import com.example.ExpenseTracker.Repository.UserRepository;
import com.example.ExpenseTracker.Responses.Security.AuthResponse;
import com.example.ExpenseTracker.Security.UserDetailsManagerService;
import com.example.ExpenseTracker.Utility.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceManager implements BaseUserCRUDServiceManager,AuthenticateUser{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsManagerService userDetailsManagerService;

    public UserServiceManager(UserRepository userRepository,
                              PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              JwtUtil jwtUtil,
                              UserDetailsManagerService userDetailsManagerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsManagerService = userDetailsManagerService;
    }

    @Override
    public User findUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()->
                new GlobalExceptionHandler("User " +
                        "not found.", HttpStatus.NOT_FOUND,
                        "NOT_FOUND"));
    }


    @Override
    public void saveUser(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if(!userRepository.existsByUsernameAndEmail(username,email)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new GlobalExceptionHandler( "User already" +
                    " registered.", HttpStatus.CONFLICT,
                    "USER_ALREADY_EXISTS");
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
            throw new GlobalExceptionHandler( "User " +
                    "information could not be updated.", HttpStatus.NOT_FOUND,
                    "NOT_FOUND");
        }
    }


    @Override
    public AuthResponse createAuthentication(AuthRequest authRequest) {
        User user;
        try {
            user = userRepository.findByUsername
                    (authRequest.getUsername()).get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest
                            .getUsername(), authRequest.getPassword())
            );
        } catch(Exception e){
            throw new GlobalExceptionHandler(
                    "Incorrect password or username.",
                    HttpStatus.UNAUTHORIZED,
                    "UNAUTHORIZED");
        }

        final UserDetails userDetails = userDetailsManagerService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthResponse(jwt,user.getId());
    }
}
