package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/sauce")
public interface DippingSauceController {

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createDippingSauce(@RequestBody DippingSauceDto dippingSauceDto);
}
