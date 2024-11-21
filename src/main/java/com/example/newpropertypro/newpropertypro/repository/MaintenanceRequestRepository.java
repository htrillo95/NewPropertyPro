package com.example.newpropertypro.newpropertypro.repository;

import com.example.newpropertypro.newpropertypro.models.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {
    // Additional query methods can be added here if needed
}