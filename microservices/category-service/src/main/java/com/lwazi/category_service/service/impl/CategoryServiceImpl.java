package com.lwazi.category_service.service.impl;


import org.springframework.stereotype.Service;

import java.util.Set;

import com.lwazi.category_service.model.Category;
import com.lwazi.category_service.payloadDTO.SalonDTO;
import com.lwazi.category_service.repository.CategoryRepository;
import com.lwazi.category_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, SalonDTO salonDTO) {
        
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDTO.getId());
        return this.categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        
        return this.categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {

        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new Exception("Category not found with id: " + id);
        }
        return category;
    }

    @Override
    public void deleteCategory(Long id, Long salonId) throws Exception {
        Category category = this.getCategoryById(id);
        if (!category.getSalonId().equals(salonId)) {
            throw new Exception("You are not authorized to delete this category!");
        }
        this.categoryRepository.delete(category);
    }
    
    
    
    
}
