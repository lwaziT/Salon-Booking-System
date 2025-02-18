package com.lwazi.payment_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping()
    public String HomeControllerHandler() {
        return "Payment microservice for salon booking system.";
    }
}
