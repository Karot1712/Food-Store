package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.order.OrderRequest;
import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "order")
public interface OrderController {
    @PostMapping(path = "/create")
    public ResponseEntity<Response> placeOrder(@RequestBody OrderRequest request);

    @GetMapping(path = "/get-all")
    public ResponseEntity<Response> getAllOrders();

    @GetMapping(path = "/get-by-id/{orderId}")
    public ResponseEntity<Response> getOrderById(@PathVariable Long orderId);
}


