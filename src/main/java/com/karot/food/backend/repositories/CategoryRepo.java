package com.karot.food.backend.repositories;


import com.karot.food.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo  extends JpaRepository<Category, Long> {
}
