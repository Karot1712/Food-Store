package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karot.food.backend.DTO.DippingSauceDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;
    private List<SauceRequest> sauces ;
}
