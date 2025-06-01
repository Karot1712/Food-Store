package com.karot.food.backend.repositories;

import com.karot.food.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
