package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    Response getAllUser();
    Response updateUserInfo(Long id, String email, String name, String password);
    User getLoginUser();
}
