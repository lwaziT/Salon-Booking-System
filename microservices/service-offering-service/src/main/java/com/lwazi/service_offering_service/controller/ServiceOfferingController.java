package com.lwazi.service_offering_service.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.service_offering_service.model.ServiceOffering;
import com.lwazi.service_offering_service.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {
    
    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
        @PathVariable Long salonId, @RequestParam(required = false) Long categoryId) {
     
        Set<ServiceOffering> services = this.serviceOfferingService.
        getAllServicesBySalonId(salonId, categoryId);

        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(
        @PathVariable Long id) throws Exception {
     
        ServiceOffering serviceOffering = this.serviceOfferingService.
        getServiceById(id);

        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
        @PathVariable Set<Long> ids) {
     
        Set<ServiceOffering> services = this.serviceOfferingService.
        getServicesByIds(ids);

        return ResponseEntity.ok(services);
    }
}
