package com.lwazi.category_service.service;

import java.util.Set;

import com.lwazi.category_service.payloadDTO.SalonDTO;

import com.lwazi.category_service.model.Category;


public interface CategoryService {
    
    Category createCategory(Category category, SalonDTO salonDTO);
    Set<Category> getCategoriesBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategory(Long id, Long salonId) throws Exception;
}
