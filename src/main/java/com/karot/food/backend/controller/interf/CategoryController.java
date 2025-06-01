package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.service.interf.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/category")
public interface CategoryController {

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDto categoryDto);

    @PostMapping(path = "/update/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCategory(@PathVariable Long categoryId,
                                                   @RequestBody CategoryDto categoryRequest
    );

    @GetMapping(path = "/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response>getAllCategory();

    @GetMapping(path = "/get-by-id/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long categoryId);

}
