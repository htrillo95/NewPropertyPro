package com.example.newpropertypro.newpropertypro.controllers;

import com.example.newpropertypro.newpropertypro.DTO.LoginRequest;
import com.example.newpropertypro.newpropertypro.models.User;
import com.example.newpropertypro.newpropertypro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Call the UserService to authenticate the user
        boolean isAuthenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); // Return a JSON object
        } else {
            response.put("error", "Invalid credentials");
            return ResponseEntity.status(401).body(response); // Return a JSON object
        }
    }
}