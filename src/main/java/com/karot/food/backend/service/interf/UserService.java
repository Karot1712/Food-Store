package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    Response signUp(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUser();
    Response getAllAdmin();
    Response updateUser(Long id, String email, String name, String password, String approve);
    Response changePassword(Long id, String password);
}
