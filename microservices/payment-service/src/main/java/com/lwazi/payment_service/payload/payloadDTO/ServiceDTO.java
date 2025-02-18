package com.lwazi.payment_service.payload.payloadDTO;

import lombok.Data;

@Data
public class ServiceDTO {
    
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int duration;
    private Long salonId;
    private Long categoryId;
    private String image;
}
