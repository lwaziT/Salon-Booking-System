package com.lwazi.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.user_service.model.User;

@RestController
public class UserController {
    
    @GetMapping("/user")
    public User getUser() {

        User user = new User();
        user.setFullName("Lwazi Ntshangase");
        user.setEmail("lwazi@QuantumQube.co.za");
        user.setPhone("081 123 4567");
        user.setRole("Client");
        return user;
    }    
}
