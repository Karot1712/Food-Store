package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserController {

    @GetMapping(path = "/get-all-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUser();

    @GetMapping(path = "/get-all-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllAdmin();

    @PostMapping(path = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response>updateUser(@RequestParam(required = false) Long id,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String password,
                                              @RequestParam(required = false) String approve
    );

    @PostMapping(path = "/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Response>changePassword(@RequestParam String oldPassword,
                                                  @RequestParam String password
    );

    @PostMapping(path = "/forgot-password")
    public ResponseEntity<Response>forgotPassword(@RequestParam String email);
}
