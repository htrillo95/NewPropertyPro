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

    // Update a property
    public Property updateProperty(Long id, Property propertyDetails) {
        return propertyRepository.findById(id).map(property -> {
            property.setName(propertyDetails.getName());
            property.setAddress(propertyDetails.getAddress());
            property.setRentAmount(propertyDetails.getRentAmount());
            property.setPropertyLink(propertyDetails.getPropertyLink());
            return propertyRepository.save(property);
        }).orElse(null);
    }

    // Delete a property
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new IllegalArgumentException("Property with ID " + id + " does not exist.");
        }
        propertyRepository.deleteById(id);
    }
}