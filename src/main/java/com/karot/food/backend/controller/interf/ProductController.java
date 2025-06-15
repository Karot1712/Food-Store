package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.ProductDto;
import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RequestMapping(path = "/product")
public interface ProductController {

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createProduct(@RequestParam Long categoryId,
                                                  @RequestParam String name,
                                                  @RequestParam String description,
                                                  @RequestParam Integer price,
                                                  @RequestParam MultipartFile image,
                                                  @RequestParam String status
    );

    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(@RequestParam(required = false) Long categoryId,
                                                  @RequestParam Long productId,
                                                  @RequestParam(required = false) MultipartFile image,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String description,
                                                  @RequestParam(required = false) Integer price,
                                                  @RequestParam(required = false) String status

    );

    @DeleteMapping(path = "/delete/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long productId);

    @GetMapping(path = "/get-all")
    public ResponseEntity<Response> getAllProduct();

    @GetMapping(path = "/get/{productId}")
    public ResponseEntity<Response> getProductById(@PathVariable Long productId);

    @GetMapping(path = "/get-by-category/{categoryId}")
    public ResponseEntity<Response> getProductByCategory(@PathVariable Long categoryId);
}
