package com.example.newpropertypro.newpropertypro.repository;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

    // Custom query to find maintenance requests by tenant ID
    List<MaintenanceRequest> findByTenantId(Long tenantId);
}