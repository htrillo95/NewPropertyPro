package com.example.newpropertypro.newpropertypro.service;

import com.example.newpropertypro.newpropertypro.models.User;
import com.example.newpropertypro.newpropertypro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticate user by checking if the username exists and if the provided password matches.
     */
    public boolean authenticateUser(String username, String password) {
        // For now, comparing plain text passwords (not secure for production)
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    /**
     * Save a new user to the database. You may want to add a check here to avoid duplicate usernames.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Find a user by their username.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}