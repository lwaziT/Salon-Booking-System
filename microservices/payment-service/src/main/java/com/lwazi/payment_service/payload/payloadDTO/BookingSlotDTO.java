package com.lwazi.payment_service.payload.payloadDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingSlotDTO {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
