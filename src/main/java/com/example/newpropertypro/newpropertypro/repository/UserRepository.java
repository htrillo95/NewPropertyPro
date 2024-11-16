package com.example.newpropertypro.newpropertypro.repository;

import com.example.newpropertypro.newpropertypro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}