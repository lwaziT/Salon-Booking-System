package com.lwazi.service_offering_service.service;


import java.util.Set;

import com.lwazi.service_offering_service.model.ServiceOffering;
import com.lwazi.service_offering_service.payloadDTO.CategoryDTO;
import com.lwazi.service_offering_service.payloadDTO.SalonDTO;
import com.lwazi.service_offering_service.payloadDTO.ServiceDTO;

public interface ServiceOfferingService {
    
    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, 
    CategoryDTO categoryDTO);
    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;
    Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId);
    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long id) throws Exception;
}
