package com.lwazi.booking_service.mapper;

import com.lwazi.booking_service.model.Booking;
import com.lwazi.booking_service.payloadDTO.BookingDTO;

public class BookingMapper {

    public static BookingDTO mapToBookingDTO(Booking booking) {

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setTotalPrice(booking.getTotalPrice());

        return bookingDTO;
    }
    
}
