package com.lwazi.booking_service.model;

import lombok.Data;

@Data
public class SalonReport {
    
    private Long salonId;
    private String salonName;
    private Double totalEarnings;
    private int totalBookings;
    private int totalCancellations;
    private Double totalRefund;
}
