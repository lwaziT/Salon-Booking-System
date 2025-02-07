package com.lwazi.salon_service.service;

import java.util.List;

import com.lwazi.salon_service.model.Salon;
import com.lwazi.salon_service.payloadDTO.SalonDTO;
import com.lwazi.salon_service.payloadDTO.UserDTO;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception;

    Salon getSalonById(Long salonId);

    List<Salon> getAllSalons();

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> getSalonsByCity(String city);

}
