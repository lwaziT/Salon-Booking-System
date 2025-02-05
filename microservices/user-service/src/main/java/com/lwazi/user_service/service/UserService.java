package com.lwazi.user_service.service;

import java.util.List;

import com.lwazi.user_service.model.User;

public interface UserService {
    
    User createUser(User user);
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();
    User updateUser(User user, Long id) throws Exception;
    String deleteUser(Long id) throws Exception;
}
