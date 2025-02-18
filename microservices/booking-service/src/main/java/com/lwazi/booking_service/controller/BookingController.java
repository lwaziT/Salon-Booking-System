package com.lwazi.booking_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.booking_service.domain.BookingStatus;
import com.lwazi.booking_service.mapper.BookingMapper;
import com.lwazi.booking_service.model.Booking;
import com.lwazi.booking_service.model.SalonReport;
import com.lwazi.booking_service.payloadDTO.BookingDTO;
import com.lwazi.booking_service.payloadDTO.BookingRequest;
import com.lwazi.booking_service.payloadDTO.BookingSlotDTO;
import com.lwazi.booking_service.payloadDTO.SalonDTO;
import com.lwazi.booking_service.payloadDTO.ServiceDTO;
import com.lwazi.booking_service.payloadDTO.UserDTO;
import com.lwazi.booking_service.service.BookingService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    
    private final BookingService bookingService;

    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId,
    @RequestBody BookingRequest bookingRequest) throws Exception {
        
        UserDTO user = new UserDTO();
        user.setId(1L);

        SalonDTO salon = new SalonDTO();
        salon.setId(salonId);
        salon.setOpeningTime(LocalTime.of(9, 0, 0));
        salon.setClosingTime(LocalTime.of(18, 0, 0));

        Set<ServiceDTO> services = new HashSet<>();

        ServiceDTO service = new ServiceDTO();
        service.setId(1L);
        service.setDuration(60);
        service.setPrice(350.00);
        service.setName("Haircut");

        services.add(service);

        Booking booking = this.bookingService.createBooking(bookingRequest, 
        user, salon, services);
        
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomerId() {

        List<Booking> bookings = this.bookingService.getBookingsByCustomerId(1L);

        return ResponseEntity.ok(this.getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {

        return bookings.stream().map(booking -> {
            return BookingMapper.mapToBookingDTO(booking);
        }).collect(Collectors.toSet());
    }
    
    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalonId() {

        List<Booking> bookings = this.bookingService.getBookingsBySalonId(1L);

        return ResponseEntity.ok(this.getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {

        Booking booking = this.bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.mapToBookingDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
    @RequestParam BookingStatus status) throws Exception {

        Booking booking = this.bookingService.updateBookingStatus(bookingId, status);

        return ResponseEntity.ok(BookingMapper.mapToBookingDTO(booking));
    }

    @GetMapping("/slot/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(@PathVariable Long salonId,
    @RequestParam(required = false) LocalDate date) throws Exception {

        List<Booking> booking = this.bookingService.getBookingsByDate(date, salonId);

        List<BookingSlotDTO> bookings = booking.stream().map(slot -> {
            BookingSlotDTO slotDTO = new BookingSlotDTO();
            slotDTO.setStartTime(slot.getStartTime());
            slotDTO.setEndTime(slot.getEndTime());
            return slotDTO;
        }).collect(Collectors.toList());


        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() {

        SalonReport report = this.bookingService.getSalonReport(1L);

        return ResponseEntity.ok(report);
    }
}
