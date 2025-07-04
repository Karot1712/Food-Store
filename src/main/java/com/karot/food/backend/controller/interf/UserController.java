package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/user")
public interface UserController {

    @GetMapping(path = "/get-all-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUser();

    @PostMapping(path = "/update")
    public ResponseEntity<Response>updateUserInfo(@RequestParam Long id,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String password

    );


}
