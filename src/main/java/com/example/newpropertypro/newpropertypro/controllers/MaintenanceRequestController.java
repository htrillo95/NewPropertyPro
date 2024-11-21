package com.example.newpropertypro.newpropertypro.controllers;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import com.example.newpropertypro.newpropertypro.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/maintenance-request")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    // Get all maintenance requests
    @GetMapping
    public ResponseEntity<?> getAllRequests() {
        return ResponseEntity.ok(maintenanceRequestService.getAllRequests());
    }

    // Get a specific maintenance request by ID
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> getRequestById(@PathVariable Long id) {
        return maintenanceRequestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new maintenance request
    @PostMapping
    public ResponseEntity<MaintenanceRequest> createRequest(@RequestBody MaintenanceRequest request) {
        return ResponseEntity.ok(maintenanceRequestService.saveRequest(request));
    }

    // Update the status of a maintenance request
    @PutMapping("/{id}/status")
    public ResponseEntity<MaintenanceRequest> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Optional<MaintenanceRequest> requestOptional = maintenanceRequestService.getRequestById(id);

        if (requestOptional.isPresent()) {
            MaintenanceRequest request = requestOptional.get();
            request.setStatus(status); // Update the status
            maintenanceRequestService.saveRequest(request); // Save the updated request
            return ResponseEntity.ok(request); // Return the updated request
        }

        return ResponseEntity.notFound().build(); // Return 404 if not found
    }

    // Delete a maintenance request by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}