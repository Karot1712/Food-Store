package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karot.food.backend.DTO.AddressDto;
import com.karot.food.backend.model.Address;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    private Integer totalPrice;
    private String contactNumber;
    private String paymentMethod;
    private Address address;
    private List<OrderItemRequest> itemRequests = new ArrayList<>();
}
