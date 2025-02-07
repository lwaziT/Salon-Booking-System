package com.lwazi.salon_service.mapper;

import com.lwazi.salon_service.model.Salon;
import com.lwazi.salon_service.payloadDTO.SalonDTO;

public class SalonMapper {
    
    public static SalonDTO mapToDTO(Salon salon){
        SalonDTO dto = new SalonDTO();
        dto.setId(salon.getId());
        dto.setName(salon.getName());
        dto.setImages(salon.getImages());
        dto.setAddress(salon.getAddress());
        dto.setContact(salon.getContact());
        dto.setEmail(salon.getEmail());
        dto.setCity(salon.getCity());
        dto.setOwnerId(salon.getOwnerId());
        dto.setOpeningTime(salon.getOpeningTime());
        dto.setClosingTime(salon.getClosingTime());
        
        return dto;
    }
}
