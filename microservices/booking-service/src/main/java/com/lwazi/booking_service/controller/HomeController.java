package com.lwazi.booking_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping
    public String HomeControllerHandler() {
        return "Welcome to the Booking Service";
    }
}
