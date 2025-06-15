package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.order.OrderRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.OrderController;
import com.karot.food.backend.service.interf.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    public ResponseEntity<Response> placeOrder(OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }
}
