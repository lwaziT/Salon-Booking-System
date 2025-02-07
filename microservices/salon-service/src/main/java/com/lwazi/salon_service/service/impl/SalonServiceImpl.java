package com.lwazi.salon_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwazi.salon_service.model.Salon;
import com.lwazi.salon_service.payloadDTO.SalonDTO;
import com.lwazi.salon_service.payloadDTO.UserDTO;
import com.lwazi.salon_service.repository.SalonRepository;
import com.lwazi.salon_service.service.SalonService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    
    @Override
    public Salon createSalon(SalonDTO req, UserDTO user){

        Salon newSalon = new Salon();
        newSalon.setName(req.getName());
        newSalon.setImages(req.getImages());
        newSalon.setAddress(req.getAddress());
        newSalon.setContact(req.getContact());
        newSalon.setEmail(req.getEmail());
        newSalon.setCity(req.getCity());
        newSalon.setOwnerId(user.getId());
        newSalon.setOpeningTime(req.getOpeningTime());
        newSalon.setClosingTime(req.getClosingTime());

        return salonRepository.save(newSalon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception{

        Salon updatedSalon = salonRepository.findById(salonId).orElse(null);
        if(updatedSalon != null && salon.getOwnerId().equals(user.getId())){
            updatedSalon.setName(salon.getName());
            updatedSalon.setImages(salon.getImages());
            updatedSalon.setAddress(salon.getAddress());
            updatedSalon.setContact(salon.getContact());
            updatedSalon.setEmail(salon.getEmail());
            updatedSalon.setCity(salon.getCity());
            updatedSalon.setOwnerId(user.getId());
            updatedSalon.setOpeningTime(salon.getOpeningTime());
            updatedSalon.setClosingTime(salon.getClosingTime());

            return salonRepository.save(updatedSalon);
        }
        
        throw new Exception("Salon does not exist!");
    }

    @Override
    public Salon getSalonById(Long salonId){

        Salon salon = salonRepository.findById(salonId).orElse(null);

        if (salon == null){

            throw new RuntimeException("Salon does not exist!");
        }
        return salon;
    }

    @Override
    public List<Salon> getAllSalons(){

        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId){

        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> getSalonsByCity(String city){

        return salonRepository.searchSalons(city);
    }

}
