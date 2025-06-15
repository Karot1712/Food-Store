package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/sauce")
public interface DippingSauceController {

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createDippingSauce(@RequestParam String name,
                                                       @RequestParam Integer price
    );

    @DeleteMapping(path = "/delete/{sauceId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteDippingSauce(@PathVariable Long sauceId);

    @GetMapping(path = "/get-all")
    public ResponseEntity<Response> getAllDippingSauce();

    @GetMapping(path = "/get-by-id/{sauceId}")
    public ResponseEntity<Response> getSauceById(@PathVariable Long sauceId);

}
