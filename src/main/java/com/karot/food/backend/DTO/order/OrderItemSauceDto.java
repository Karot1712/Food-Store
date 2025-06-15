package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemSauceDto {
    private Long sauceId;
    private Integer quantity;
    private Integer price;

}
