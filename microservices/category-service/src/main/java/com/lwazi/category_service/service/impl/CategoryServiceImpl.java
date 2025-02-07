package com.lwazi.category_service.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.lwazi.category_service.model.Category;
import com.lwazi.category_service.payloadDTO.SalonDTO;
import com.lwazi.category_service.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Override
    public Category postCategory(Category category, SalonDTO salonDTO) {
        return null;
    }

    @Override
    public Set<Category> getAllCategoriesBySalon() {
        return Set.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        
    }
}
