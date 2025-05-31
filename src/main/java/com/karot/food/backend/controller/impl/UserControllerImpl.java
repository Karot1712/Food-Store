package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.UserController;
import com.karot.food.backend.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Autowired
    private final UserService userService;

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


}
