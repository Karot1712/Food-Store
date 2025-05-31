package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.Response;

public interface CategoryService {
    Response createCategory(CategoryDto categoryDto);
}
