package com.example.newpropertypro.newpropertypro.service;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import com.example.newpropertypro.newpropertypro.repository.MaintenanceRequestRepository;
import com.example.newpropertypro.newpropertypro.repository.UserRepository; // For tenant user details
import com.example.newpropertypro.newpropertypro.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaintenanceRequestService {

    private final MaintenanceRequestRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public MaintenanceRequestService(MaintenanceRequestRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // Save or update a maintenance request
    public MaintenanceRequest saveRequest(MaintenanceRequest request) {
        return repository.save(request);
    }

    // Get all maintenance requests with usernames
    public List<Map<String, Object>> getAllRequestsWithUsers() {
        List<MaintenanceRequest> requests = repository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (MaintenanceRequest request : requests) {
            Optional<User> user = userRepository.findById(request.getTenantId());
            // Add logging to verify the data flow
            System.out.println("Fetching user for tenantId: " + request.getTenantId());
            System.out.println("User found: " + user.map(User::getUsername).orElse("None"));

            Map<String, Object> requestWithUser = new HashMap<>();
            requestWithUser.put("id", request.getId());
            requestWithUser.put("description", request.getDescription());
            requestWithUser.put("status", request.getStatus());
            requestWithUser.put("submittedBy", user.map(User::getUsername).orElse("Unknown")); // Add username
            requestWithUser.put("createdAt", request.getCreatedAt());
            result.add(requestWithUser);
        }

        return result;
    }

    // Get maintenance requests for a specific tenant
    public List<MaintenanceRequest> getRequestsByTenantId(Long tenantId) {
        return repository.findByTenantId(tenantId);
    }

    // Update status of a request
    public Optional<MaintenanceRequest> updateStatus(Long id, String newStatus) {
        Optional<MaintenanceRequest> requestOptional = repository.findById(id);
        requestOptional.ifPresent(request -> {
            request.setStatus(newStatus);
            repository.save(request);
        });
        return requestOptional;
    }

    // Delete a maintenance request by ID
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}