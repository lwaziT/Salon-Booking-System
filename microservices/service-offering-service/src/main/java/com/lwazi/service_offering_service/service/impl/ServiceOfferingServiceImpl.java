package com.lwazi.service_offering_service.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lwazi.service_offering_service.model.ServiceOffering;
import com.lwazi.service_offering_service.payloadDTO.CategoryDTO;
import com.lwazi.service_offering_service.payloadDTO.SalonDTO;
import com.lwazi.service_offering_service.payloadDTO.ServiceDTO;
import com.lwazi.service_offering_service.repository.ServiceOfferingRepository;
import com.lwazi.service_offering_service.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, 
    ServiceDTO serviceDTO, CategoryDTO categoryDTO) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setImage(serviceDTO.getImage());
        
        return this.serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, 
    ServiceOffering service) throws Exception {
        ServiceOffering serviceOffering = this.serviceOfferingRepository.
        findById(serviceId).orElse(null);
        if (serviceOffering == null) {
            throw new Exception("Service does not exist with id: " + serviceId);
        }

        serviceOffering.setName(service.getName());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setImage(service.getImage());
        

        return this.serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> services = this.serviceOfferingRepository.findBySalonId(salonId);
        if (categoryId != null) {
            services = services.stream().filter(service -> service.getCategoryId() != null &&
            service.getCategoryId().equals(categoryId)).collect(Collectors.toSet());
            
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {

        List<ServiceOffering> services = this.serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {

        ServiceOffering serviceOffering = this.serviceOfferingRepository.
        findById(id).orElse(null);
        if (serviceOffering == null) {
            throw new Exception("Service does not exist with id: " + id);
        }
        return serviceOffering;
    }
    
}
