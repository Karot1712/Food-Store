package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private Integer price;
    private Integer quantity;
    private ProductDto product;
    private List<OrderItemSauceDto> sauceDtos;
}
