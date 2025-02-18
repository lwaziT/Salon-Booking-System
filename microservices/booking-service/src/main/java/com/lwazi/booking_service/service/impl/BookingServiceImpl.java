package com.lwazi.booking_service.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lwazi.booking_service.domain.BookingStatus;
import com.lwazi.booking_service.model.Booking;
import com.lwazi.booking_service.model.SalonReport;
import com.lwazi.booking_service.payloadDTO.BookingRequest;
import com.lwazi.booking_service.payloadDTO.SalonDTO;
import com.lwazi.booking_service.payloadDTO.ServiceDTO;
import com.lwazi.booking_service.payloadDTO.UserDTO;
import com.lwazi.booking_service.repository.BookingRepository;
import com.lwazi.booking_service.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, 
    Set<ServiceDTO> services) throws Exception {
       
        int totalDuration = services.stream().mapToInt(ServiceDTO::getDuration).sum();

        LocalDateTime startTime = booking.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(totalDuration);

        this.isTimeSlotAvailable(salon, startTime, endTime);

        Double totalPrice = services.stream().mapToDouble(ServiceDTO::getPrice).sum();

        Set<Long> serviceIds = services.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setStartTime(startTime);
        newBooking.setEndTime(endTime);
        newBooking.setServiceIds(serviceIds);
        newBooking.setTotalPrice(totalPrice);
        newBooking.setStatus(BookingStatus.PENDING);

        return this.bookingRepository.save(newBooking);
    }

    private Boolean isTimeSlotAvailable(SalonDTO salon, LocalDateTime startTime, LocalDateTime endTime) throws Exception {

        List<Booking> existingBookings = this.getBookingsBySalonId(salon.getId());

        LocalDateTime salonOpenTime = salon.getOpeningTime().atDate(startTime.toLocalDate());
        LocalDateTime salonCloseTime = salon.getClosingTime().atDate(endTime.toLocalDate());

        if (startTime.isBefore(salonOpenTime) || 
        endTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time is outside of salon operating hours");
        }

        for (Booking booking : existingBookings) {

            LocalDateTime bookingStartTime = booking.getStartTime();
            LocalDateTime bookingEndTime = booking.getEndTime();

            if (startTime.isBefore(bookingEndTime) && endTime.isAfter(bookingStartTime)) {
                throw new Exception("Booking slot is not available");
            }
            if (startTime.isEqual(bookingStartTime) || endTime.isEqual(bookingEndTime)) {
                throw new Exception("Booking slot is not available");
            }
        }

        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {

        return this.bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalonId(Long salonId) {

        return this.bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long bookingId) throws Exception {

        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new Exception("Booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBookingStatus(Long bookingId, BookingStatus status) throws Exception {

        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new Exception("Booking not found");
        }
        booking.setStatus(status);
        return this.bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {

        List<Booking> bookings = this.bookingRepository.findBySalonId(salonId);
        if (date == null) {
            return bookings;
        }
        return bookings.stream().filter(booking -> booking.getStartTime().toLocalDate().equals(date))
        .collect(Collectors.toList());
       
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {

        List<Booking> bookings = this.bookingRepository.findBySalonId(salonId);

        Double totalEarnings = bookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        int totalBookings = bookings.size();

        List<Booking> cancelledBookings = bookings.stream().filter(booking -> booking.getStatus()
        .equals(BookingStatus.CANCELLED)).collect(Collectors.toList());

        Double totalRefunds = cancelledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        SalonReport salonReport = new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalBookings(totalBookings);    
        salonReport.setTotalRefund(totalRefunds);
        salonReport.setTotalCancellations(cancelledBookings.size());


        return salonReport;
    }
    
}
