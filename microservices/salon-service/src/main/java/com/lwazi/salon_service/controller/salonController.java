package com.lwazi.salon_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.salon_service.mapper.SalonMapper;
import com.lwazi.salon_service.model.Salon;
import com.lwazi.salon_service.payloadDTO.SalonDTO;
import com.lwazi.salon_service.payloadDTO.UserDTO;
import com.lwazi.salon_service.service.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class salonController {
    
    private final SalonService salonService; 

    @PostMapping("/salon")
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salon) {

        UserDTO user = new UserDTO();
        user.setId(1L);
        Salon newSalon = salonService.createSalon(salon, user);
        SalonDTO dto = SalonMapper.mapToDTO(newSalon);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable("id") Long salonId,
                                                @RequestBody SalonDTO salon) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L);
        Salon newSalon = salonService.updateSalon(salon, user, salonId);
        SalonDTO dto = SalonMapper.mapToDTO(newSalon);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/salon")
    public ResponseEntity<List<SalonDTO>> getAllSalons() throws Exception {

        List<Salon> salonLists = salonService.getAllSalons();
        List<SalonDTO> dto = salonLists.stream().map((salonList) -> {
            SalonDTO salonDTO = SalonMapper.mapToDTO(salonList);
            return salonDTO;
        }).toList();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/salon/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId) throws Exception {

        Salon newSalon = salonService.getSalonById(salonId);
        SalonDTO dto = SalonMapper.mapToDTO(newSalon);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String city) throws Exception {

        List<Salon> salonLists = salonService.getSalonsByCity(city);

        List<SalonDTO> dto = salonLists.stream().map((salonList) -> {
            SalonDTO salonDTO = SalonMapper.mapToDTO(salonList);
            return salonDTO;
        }).toList();
        
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/salon/owner/{id}")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@PathVariable Long id) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(id);
        SalonDTO dto = SalonMapper.mapToDTO(salon);
        
        return ResponseEntity.ok(dto);
    }

}
