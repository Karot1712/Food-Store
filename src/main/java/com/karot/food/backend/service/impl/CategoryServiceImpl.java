package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.Category;
import com.karot.food.backend.repositories.CategoryRepo;
import com.karot.food.backend.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final EntityDtoMapper entityDtoMapper;
    private final CategoryRepo categoryRepo;

    @Override
    public Response createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        System.out.println(category.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category id is not exist"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();
    }

    @Override
    public Response getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto>categoryDtos =categories.stream()
                .map(entityDtoMapper::mapCategoryToDto)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .categoryList(categoryDtos)
                .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category id is not exist"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDto(category);
        return Response.builder()
                .category(categoryDto)
                .build();
    }
}
