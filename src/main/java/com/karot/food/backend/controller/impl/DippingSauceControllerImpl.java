package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.DippingSauceController;
import com.karot.food.backend.service.interf.DippingSauceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DippingSauceControllerImpl implements DippingSauceController {
    private final DippingSauceService dippingSauceService;

    @Override
    public ResponseEntity<Response> createDippingSauce(String name,Integer price) {
        return ResponseEntity.ok(dippingSauceService.createDippingSauce(name, price));
    }

    @Override
    public ResponseEntity<Response> deleteDippingSauce(Long sauceId) {
        return ResponseEntity.ok(dippingSauceService.deleteDippingSauce(sauceId));
    }

    @Override
    public ResponseEntity<Response> getAllDippingSauce() {
        return ResponseEntity.ok(dippingSauceService.getAllDippingSauce());
    }

    @Override
    public ResponseEntity<Response> getSauceById(Long sauceId) {
        return ResponseEntity.ok(dippingSauceService.getSauceById(sauceId));
    }
}
