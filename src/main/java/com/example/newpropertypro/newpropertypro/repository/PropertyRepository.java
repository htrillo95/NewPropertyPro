package com.example.newpropertypro.newpropertypro.repository;

import com.example.newpropertypro.newpropertypro.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}

//This interface extends JpaRepository, so it will provide CRUD operations without additional code.