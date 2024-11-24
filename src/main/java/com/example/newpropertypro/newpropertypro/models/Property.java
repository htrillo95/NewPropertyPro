package com.example.newpropertypro.newpropertypro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "property") // Optional: Explicitly define table name
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Property name (required)

    private String address; // Optional property address

    private Double rentAmount; // Optional rent amount

    @Column(nullable = false)
    private String propertyLink; // Link to external listing (e.g., Zillow)

    private String imageUrl; // Optional image URL for preview

    // Constructors
    public Property() {}

    public Property(String name, String propertyLink, String imageUrl) {
        this.name = name;
        this.propertyLink = propertyLink;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getPropertyLink() {
        return propertyLink;
    }

    public void setPropertyLink(String propertyLink) {
        this.propertyLink = propertyLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}