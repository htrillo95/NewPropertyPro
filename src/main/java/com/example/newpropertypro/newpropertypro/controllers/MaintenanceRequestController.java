package com.example.newpropertypro.newpropertypro.controllers;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import com.example.newpropertypro.newpropertypro.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // To support Map in responses

@RestController
@RequestMapping("/api/maintenance-request")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    // Get all maintenance requests (Admin) with tenant usernames
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllRequests() {
        List<Map<String, Object>> requestsWithUsers = maintenanceRequestService.getAllRequestsWithUsers();
        return ResponseEntity.ok(requestsWithUsers);
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
        return maintenanceRequestService.updateStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a maintenance request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}