package com.karot.food.backend.mapper;

import com.karot.food.backend.DTO.CategoryDto;
import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.ProductDto;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.model.Category;
import com.karot.food.backend.model.DippingSauce;
import com.karot.food.backend.model.Product;
import com.karot.food.backend.model.User;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    //User -> DTO
    public UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole().name());

        return userDto;
    }

    //Category -> DTO
    public CategoryDto mapCategoryToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    //Product -> DTO
    public ProductDto mapProductToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setStatus(productDto.getStatus());

        return productDto;
    }

    //DippingSauce -> DTO
    public DippingSauceDto mapDippingSauceToDto(DippingSauce dippingSauce){
        DippingSauceDto dippingSauceDto = new DippingSauceDto();

        dippingSauceDto.setId(dippingSauce.getId());
        dippingSauceDto.setName(dippingSauce.getName());
        dippingSauceDto.setPrice(dippingSauce.getPrice());

        return dippingSauceDto;
    }

}
