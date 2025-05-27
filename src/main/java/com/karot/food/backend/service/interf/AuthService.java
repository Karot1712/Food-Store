package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;

public interface AuthService {
    Response signUp(UserDto registrationRequest);
    Response login(LoginRequest loginRequest);
    Response approve(String email,String code);

}
