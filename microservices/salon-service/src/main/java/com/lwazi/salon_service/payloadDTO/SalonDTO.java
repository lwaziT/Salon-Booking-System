package com.lwazi.salon_service.payloadDTO;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class SalonDTO {
    
    private Long id;
    
    private String name;

    private List<String> images;

    private String address;

    private String contact;
    
    private String email;
    
    private String city;
    
    private Long ownerId;
    
    private LocalTime openingTime;

    private LocalTime closingTime;
}
