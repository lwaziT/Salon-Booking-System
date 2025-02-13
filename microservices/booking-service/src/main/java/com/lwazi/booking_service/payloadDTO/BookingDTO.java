package com.lwazi.booking_service.payloadDTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.lwazi.booking_service.domain.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

    private Long id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceIds;

    private BookingStatus status = BookingStatus.PENDING;

    private Double totalPrice;
}
