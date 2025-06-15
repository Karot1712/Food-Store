package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.ProductDto;
import com.karot.food.backend.DTO.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface ProductService {
    Response createProduct(Long categoryId, String name, String description, Integer price, MultipartFile image, String status);
    Response updateProduct(Long categoryId, Long productId, MultipartFile image, String name, String description, Integer price, String status);
    Response deleteProduct(Long productId);
    Response getAllProduct();
    Response getProductById(Long productId);
    Response getProductByCategory(Long categoryId);
}
