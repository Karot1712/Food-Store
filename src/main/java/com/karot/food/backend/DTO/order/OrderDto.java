package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.karot.food.backend.DTO.AddressDto;
import com.karot.food.backend.DTO.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String paymentMethod;
    private String contactNumber;
    private AddressDto address;
    private Integer totalPrice;

    private UserDto user;
    private List<OrderItemDto> orderItemList;
    private LocalDateTime createdAt;

}
