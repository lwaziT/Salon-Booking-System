package com.lwazi.user_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.user_service.model.User;
import com.lwazi.user_service.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);
        
    }
    
    @GetMapping("/user")
    public List<User> getUser() {

        return userRepository.findAll();
    }    

    @GetMapping("/user/{id}")
    public User getByIdUser(@PathVariable Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new Exception("User not found with id: " + id);

    }
    
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) 
    throws Exception {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new Exception("User not found with id: " + id);
        }

        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new Exception("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
