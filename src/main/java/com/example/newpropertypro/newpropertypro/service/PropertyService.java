package com.example.newpropertypro.newpropertypro.service;

import com.example.newpropertypro.newpropertypro.models.Property;
import com.example.newpropertypro.newpropertypro.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Save or update a property
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    // Find a property by ID
    public Optional<Property> findPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    // Find all properties
    public List<Property> findAllProperties() {
        return propertyRepository.findAll();
    }

    // Delete a property by ID
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new IllegalArgumentException("Property with ID " + id + " does not exist.");
        }
        propertyRepository.deleteById(id);
    }

    // Delete multiple properties
    public void deleteProperties(List<Long> ids) {
        ids.forEach(id -> {
            if (propertyRepository.existsById(id)) {
                propertyRepository.deleteById(id);
            }
        });
    }
}