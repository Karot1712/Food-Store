package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.controller.interf.AuthController;
import com.karot.food.backend.service.interf.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ResponseEntity<Response> signUp(UserDto registrationRequest) {
        return ResponseEntity.ok(authService.signUp(registrationRequest));
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    public ResponseEntity<Response> approve(String email,String code) {
        return ResponseEntity.ok(authService.approve(email,code));
    }

}
