package com.lwazi.service_offering_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.service_offering_service.model.ServiceOffering;
import com.lwazi.service_offering_service.payloadDTO.CategoryDTO;
import com.lwazi.service_offering_service.payloadDTO.SalonDTO;
import com.lwazi.service_offering_service.payloadDTO.ServiceDTO;
import com.lwazi.service_offering_service.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    
    @PostMapping()
    public ResponseEntity<ServiceOffering> createService(
        @RequestBody ServiceDTO serviceDTO) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());
     
        ServiceOffering service = this.serviceOfferingService.
        createService(salonDTO, serviceDTO, categoryDTO);

        return ResponseEntity.ok(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
        @PathVariable Long id, @RequestBody ServiceOffering serviceOffering
        ) throws Exception {
     
        ServiceOffering service = this.serviceOfferingService.updateService(id, serviceOffering);

        return ResponseEntity.ok(service);
    }
    
}
