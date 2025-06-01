package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.CategoryController;
import com.karot.food.backend.service.interf.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<Response> createCategory(CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @Override
    public ResponseEntity<Response> updateCategory(Long categoryId, CategoryDto categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }

    @Override
    public ResponseEntity<Response> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @Override
    public ResponseEntity<Response> getCategoryById(Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
}
