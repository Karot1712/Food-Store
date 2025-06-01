package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.ProductDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.ProductController;
import com.karot.food.backend.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<Response> createProduct(Long categoryId, String name, String description, Integer price, MultipartFile image, String status) {
        return ResponseEntity.ok(productService.createProduct(categoryId,name,description,price,image,status));
    }

    @Override
    public ResponseEntity<Response> updateProduct(Long categoryId, Long productId, MultipartFile image, String name, String description, Integer price, String status) {
        return ResponseEntity.ok(productService.updateProduct(categoryId,productId,image,name,description,price,status));
    }

    @Override
    public ResponseEntity<Response> deleteProduct(Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @Override
    public ResponseEntity<Response> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @Override
    public ResponseEntity<Response> getProductById(Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
