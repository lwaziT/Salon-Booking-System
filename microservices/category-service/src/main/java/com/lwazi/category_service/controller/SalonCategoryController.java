package com.lwazi.category_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwazi.category_service.model.Category;
import com.lwazi.category_service.payloadDTO.SalonDTO;
import com.lwazi.category_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories/salon-owner")
public class SalonCategoryController {
    
    private final CategoryService categoryService;
    
    @PostMapping()
    public ResponseEntity<Category> createCategory(
        @RequestBody  Category category) {

            SalonDTO salonDTO = new SalonDTO();
            salonDTO.setId(1L);

            Category newCategory = this.categoryService.createCategory(category, salonDTO);

            return ResponseEntity.ok(newCategory);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
        @PathVariable Long id) throws Exception {

            SalonDTO salonDTO = new SalonDTO();
            salonDTO.setId(1L);

            this.categoryService.deleteCategory(id, salonDTO.getId());

            return ResponseEntity.ok("Deleted successfully");
    } 

}
