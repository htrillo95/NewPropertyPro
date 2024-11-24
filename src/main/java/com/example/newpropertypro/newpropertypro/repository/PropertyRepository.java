package com.example.newpropertypro.newpropertypro.repository;

import com.example.newpropertypro.newpropertypro.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    // Find properties by partial name (case-insensitive)
    List<Property> findByNameContainingIgnoreCase(String name);

    // Find properties by partial address (case-insensitive)
    List<Property> findByAddressContainingIgnoreCase(String address);

    // Find properties within a rent range
    List<Property> findByRentAmountBetween(Double minRent, Double maxRent);
}