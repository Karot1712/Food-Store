package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.Category;
import com.karot.food.backend.repositories.CategoryRepo;
import com.karot.food.backend.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final EntityDtoMapper entityDtoMapper;
    private final CategoryRepo categoryRepo;

    @Override
    public Response createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(category.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }
}
