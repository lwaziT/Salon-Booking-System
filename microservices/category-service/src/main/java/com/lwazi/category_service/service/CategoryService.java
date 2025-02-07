package com.lwazi.category_service.service;

import java.util.Set;
import java.util.Locale.Category;

import com.lwazi.category_service.payloadDTO.SalonDTO;

public interface CategoryService {
    
    Category postCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon();
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id);
}
