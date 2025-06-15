package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.ProductDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.Category;
import com.karot.food.backend.model.Product;
import com.karot.food.backend.repositories.CategoryRepo;
import com.karot.food.backend.repositories.ProductRepo;
import com.karot.food.backend.service.CloudinaryService;
import com.karot.food.backend.service.interf.ProductService;
import com.karot.food.backend.utils.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final EntityDtoMapper entityDtoMapper;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final CloudinaryService cloudinaryService;

    @Override
    public Response createProduct(Long categoryId, String name, String description, Integer price, MultipartFile image, String status) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        String productImageUrl = cloudinaryService.saveImageToCloud(image);

        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setStatus(status);
        product.setImageUrl(productImageUrl);

        productRepo.save(product);
        return Response.builder()
                .status(200)
                .message("Create product successfully")
                .build();
    }

    @Override
    public Response updateProduct(Long categoryId, Long productId, MultipartFile image, String name, String description, Integer price, String status) {
        Product product = productRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Product not found"));

        Category category = null;
        String productImageUrl = null;

        if(categoryId != null){
            categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        }

        if(image != null && !image.isEmpty()){
            productImageUrl = cloudinaryService.saveImageToCloud(image);
        }

        if(category != null) product.setCategory(category);
        if(name != null) product.setName(name);
        if(description != null) product.setDescription(description);
        if(price != null) product.setPrice(price);
        if(image != null) product.setImageUrl(productImageUrl);

        productRepo.save(product);

        return Response.builder()
                .status(200)
                .message("Product update successfully")
                .build();
    }

    @Override
    public Response deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product not exist"));
        productRepo.delete(product);

        return Response.builder()
                .status(200)
                .message("Delete successfully")
                .build();
    }

    @Override
    public Response getAllProduct() {
        List<ProductDto> productList = productRepo.findAll()
                .stream()
                .map(entityDtoMapper::mapProductToDto)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .productList(productList)
                .build();
    }

    @Override
    public Response getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product not exist"));
        ProductDto productDto = entityDtoMapper.mapProductToDto(product);

        return Response.builder()
                .status(200)
                .product(productDto)
                .build();
    }

    @Override
    public Response getProductByCategory(Long categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        if(products.isEmpty()){
            throw new NotFoundException("No product found for this category");
        }
        List<ProductDto> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDto)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }
}
