package com.lwazi.user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.user_service.model.User;
import com.lwazi.user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

        User newUser = userService.createUser(user);
        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
        
    }
    
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {

        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }    

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable Long id) throws Exception {

        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) 
    throws Exception {
        
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
        
    }
}
