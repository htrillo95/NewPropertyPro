package com.example.newpropertypro.newpropertypro.service;

import com.example.newpropertypro.newpropertypro.models.User;
import com.example.newpropertypro.newpropertypro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validate user by checking if the username exists and the provided password matches.
     */
    public Optional<User> validateUser(String username, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }

    /**
     * Save a new user to the database.
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