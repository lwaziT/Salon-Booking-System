package com.lwazi.booking_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.lwazi.booking_service.domain.BookingStatus;
import com.lwazi.booking_service.model.Booking;
import com.lwazi.booking_service.model.SalonReport;
import com.lwazi.booking_service.payloadDTO.BookingRequest;
import com.lwazi.booking_service.payloadDTO.SalonDTO;
import com.lwazi.booking_service.payloadDTO.ServiceDTO;
import com.lwazi.booking_service.payloadDTO.UserDTO;

public interface BookingService {
    
    Booking createBooking(BookingRequest booking, 
    UserDTO user, SalonDTO salon, Set<ServiceDTO> services) throws Exception;

    List<Booking> getBookingsByCustomerId(Long customerId);
    List<Booking> getBookingsBySalonId(Long salonId);
    Booking getBookingById(Long bookingId) throws Exception;
    Booking updateBookingStatus(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
