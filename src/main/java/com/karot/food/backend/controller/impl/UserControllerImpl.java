package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.controller.interf.UserController;
import com.karot.food.backend.model.User;
import com.karot.food.backend.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Autowired
    private final UserService userService;

    @Override
    public ResponseEntity<Response> signUp(UserDto registrationRequest) {
        return ResponseEntity.ok(userService.signUp(registrationRequest));
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

    @Override
    public ResponseEntity<Response> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Override
    public ResponseEntity<Response> getAllAdmin() {
        return ResponseEntity.ok(userService.getAllAdmin());
    }

    @Override
    public ResponseEntity<Response> updateUser(Long id, String email, String name, String password, String approve) {
        return ResponseEntity.ok(userService.updateUser(id, email, name, password, approve));
    }

    @Override
    public ResponseEntity<Response> changePassword(Long id, String password) {
        return ResponseEntity.ok(userService.changePassword(id,password));
    }
}
