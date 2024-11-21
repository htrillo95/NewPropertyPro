package com.example.newpropertypro.newpropertypro.service;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import com.example.newpropertypro.newpropertypro.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestService {

    private final MaintenanceRequestRepository repository;

    @Autowired
    public MaintenanceRequestService(MaintenanceRequestRepository repository) {
        this.repository = repository;
    }

    // Save or update a maintenance request
    public MaintenanceRequest saveRequest(MaintenanceRequest request) {
        return repository.save(request);
    }

    // Get all maintenance requests
    public List<MaintenanceRequest> getAllRequests() {
        return repository.findAll();
    }

    // Get a maintenance request by ID
    public Optional<MaintenanceRequest> getRequestById(Long id) {
        return repository.findById(id);
    }

    // Delete a maintenance request by ID
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}