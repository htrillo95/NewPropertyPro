package com.example.newpropertypro.newpropertypro.controllers;

import com.example.newpropertypro.newpropertypro.models.Property;
import com.example.newpropertypro.newpropertypro.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Create a new property
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    // Get a property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.findPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAllProperties();
    }

    // Filter properties by query parameters
    @GetMapping("/filter")
    public List<Property> getFilteredProperties(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double minRent,
            @RequestParam(required = false) Double maxRent) {
        System.out.println("Filter endpoint hit with parameters:");
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Min Rent: " + minRent);
        System.out.println("Max Rent: " + maxRent);

        return propertyService.filterProperties(name, address, minRent, maxRent);
    }

    // Update a property by ID
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        Property updatedProperty = propertyService.updateProperty(id, propertyDetails);
        return updatedProperty != null ? ResponseEntity.ok(updatedProperty) : ResponseEntity.notFound().build();
    }

    // Delete a property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).build(); // Property not found
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Generic server error
        }
    }
}