package com.lwazi.service_offering_service.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lwazi.service_offering_service.model.ServiceOffering;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {
    
    Set<ServiceOffering> findBySalonId(Long salonId);
}
