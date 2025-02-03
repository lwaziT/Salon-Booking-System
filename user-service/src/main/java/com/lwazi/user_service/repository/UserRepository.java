package com.lwazi.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lwazi.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
