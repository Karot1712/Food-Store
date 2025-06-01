package com.karot.food.backend.service.impl;

import com.google.common.base.Strings;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.utils.VerificationUtils;
import com.karot.food.backend.security.JwtAuthFilter;
import com.karot.food.backend.security.JwtUtil;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.User;
import com.karot.food.backend.repositories.UserRepo;
import com.karot.food.backend.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class  UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response getAllUser() {
        List<UserDto> userList = userRepo.findAll()
                .stream()
                .map(entityDtoMapper::mapUserToDto)
                .toList();

        return Response.builder()
                .status(200)
                .userList(userList)
                .build();
    }

    @Override
    public Response getAllAdmin() {
        List<String> adminList = userRepo.findAllAdmin().stream().toList();
        return Response.builder()
                .status(200)
                .listOfEmail(adminList)
                .build();
    }

    @Override
    public Response updateUserInfo(Long id, String email, String name, String password) {
        User user = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User not exist"));

        if(email != null)user.setEmail(email);
        if(name != null)user.setName(name);
        if(password != null) user.setPassword(passwordEncoder.encode(password));

        userRepo.save(user);
        return Response.builder()
                .status(200)
                .message("User updated successfully")
                .build();
    }


}

