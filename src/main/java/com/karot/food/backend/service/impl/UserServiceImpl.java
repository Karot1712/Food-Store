package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.Utils.EmailUtil;
import com.karot.food.backend.enums.UserRole;
import com.karot.food.backend.security.JwtAuthFilter;
import com.karot.food.backend.security.JwtUtil;
import com.karot.food.backend.exception.InvalidCredentialException;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.User;
import com.karot.food.backend.repositories.UserRepo;
import com.karot.food.backend.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Autowired
    private final JwtAuthFilter jwtFilter;
    @Autowired
    private EmailUtil emailUtil;

    @Override
    public Response signUp(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        if(registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("Admin")){
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .id(registrationRequest.getId())
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .approve("false")
                .role(role)
                .build();

        User savedUser = userRepo.save(user);
        UserDto userDto = entityDtoMapper.mapUserToDto(savedUser);

        return Response.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new NotFoundException("User not found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Password not match");
        }
        String token = jwtUtil.generateToken(user);

        if(user.getApprove().equalsIgnoreCase("false")){
            return Response.builder()
                    .status(400)
                    .message("Wait for admin approval")
                    .build();
        }

        return Response.builder()
                .status(200)
                .message("Login success")
                .token(token)
                .expirationTime("1 week")
                .role(user.getRole().name())
                .build();
    }

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
    public Response updateUser(Long id, String email, String name, String password, String approve) {
        User user = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        List<String> list = userRepo.findAllAdmin().stream().toList();

        sendMailToAllAdmin(user.getEmail(),approve,list);

        if(email != null)user.setEmail(email);
        if(name != null)user.setName(name);
        if(password != null)user.setPassword(password);
        if(approve != null)user.setApprove(approve);

        userRepo.save(user);
        return Response.builder()
                .status(200)
                .message("User updated successfully")
                .build();
    }

    private void sendMailToAllAdmin(String user, String approve,List<String> allAdmin){
        try{
            //change to mutable
            List<String> admin = allAdmin.stream().filter(a->a.equals(jwtFilter.getCurrentUser())).collect(Collectors.toList());
            admin.remove(jwtFilter.getCurrentUser());
            
            if(approve != null && approve.equalsIgnoreCase("true")){
                emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approve","USER:- " + user + "\n is approve by \nADMIN:- " + jwtFilter.getCurrentUser(),admin);
            } else {
                emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disable","USER:- " + user + "\n is disable by \nADMIN:- " + jwtFilter.getCurrentUser(),admin);
            }
        }catch (Exception e){
            log.error("e: ", e);
        }

    }

    @Override
    public Response changePassword(Long id, String password) {
        User user = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        String oldPassword = user.getPassword();
        System.out.println(oldPassword);
        //check the old
        if(passwordEncoder.matches(password,oldPassword)){
            return Response.builder()
                    .status(400)
                    .message("The new password must be different from the current password.")
                    .build();
        } else {
            user.setPassword(password);
        }
        return Response.builder()
                .status(200)
                .message("Password changed successful")
                .build();
    }
}

