package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/auth")
public interface AuthController {

    @PostMapping(path = "/signup")
    public ResponseEntity<Response> signUp(@RequestBody UserDto registrationRequest);

    @PostMapping(path = "/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest);

    @PostMapping(path = "/approve/{email}/{code}")
    public ResponseEntity<Response>approve(@PathVariable String email,
                                           @PathVariable String code
    );


}
