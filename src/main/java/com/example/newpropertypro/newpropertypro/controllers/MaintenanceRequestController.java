package com.example.newpropertypro.newpropertypro.controllers;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import com.example.newpropertypro.newpropertypro.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenance-request")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    // Get all maintenance requests (Admin)
    @GetMapping
    public ResponseEntity<List<MaintenanceRequest>> getAllRequests() {
        List<MaintenanceRequest> requests = maintenanceRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    // Get requests by tenantId (Tenant)
    @GetMapping("/by-tenant")
    public ResponseEntity<List<MaintenanceRequest>> getRequestsByTenantId(@RequestParam Long tenantId) {
        List<MaintenanceRequest> requests = maintenanceRequestService.getRequestsByTenantId(tenantId);
        return ResponseEntity.ok(requests);
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
            request.setStatus(status);
            maintenanceRequestService.saveRequest(request);
            return ResponseEntity.ok(request);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete a maintenance request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}