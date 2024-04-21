package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.User;
import com.example.cryptotrading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // API to retrieve user information by user ID
    @GetMapping("/users/{username}")
    public User getUserById(@PathVariable String username) {

        User user = userRepository.getUserByUsername(username);

        // Throw exception if user not found
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return userRepository.getUserByUsername(username);
    }
    //// Get user information by username
    //@GetMapping("/users/username/{username}")
    //public User getUserByUsername(@PathVariable String username) {
    //    return userRepository.findByUsername(username);
    //}
}
