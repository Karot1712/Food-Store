package com.karot.food.backend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.karot.food.backend.DTO.order.OrderDto;
import com.karot.food.backend.DTO.order.OrderItemDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {
    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expirationTime;

    private UserDto user;
    private List<UserDto> userList;
    private List<String> listOfEmail;

    private CategoryDto category;
    private List<CategoryDto> categoryList;

    private ProductDto product;
    private List<ProductDto> productList;

    private DippingSauceDto sauce;
    private List<DippingSauceDto> sauceList;

    private OrderDto order;
    private List<OrderDto> orderList;

    private OrderItemDto orderItem;
    private List<OrderItemDto> orderItemList;
}
