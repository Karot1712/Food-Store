package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.order.OrderRequest;
import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "order")
public interface OrderController {
    @PostMapping(path = "/create")
    public ResponseEntity<Response> placeOrder(@RequestBody OrderRequest request);
}
