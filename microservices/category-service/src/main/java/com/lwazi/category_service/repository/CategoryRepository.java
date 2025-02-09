package com.lwazi.category_service.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lwazi.category_service.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Set<Category> findBySalonId(Long salonId);
    
}
