package com.lwazi.category_service.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.category_service.model.Category;
import com.lwazi.category_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Category>> getAllCategoriesBySalon(
        @PathVariable Long id) {
            Set<Category> categories = this.categoryService.getAllCategoriesBySalon(id);
            return ResponseEntity.ok(categories);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
        @PathVariable Long id) throws Exception {
            Category category = this.categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
    } 
}    