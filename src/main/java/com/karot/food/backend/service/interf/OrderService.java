package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.order.OrderRequest;
import com.karot.food.backend.DTO.Response;

public interface OrderService {
    Response placeOrder(OrderRequest request);
}
