package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.LoginRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.Utils.EmailUtils;
import com.karot.food.backend.Utils.VerificationUtils;
import com.karot.food.backend.enums.UserRole;
import com.karot.food.backend.exception.InvalidCredentialException;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.User;
import com.karot.food.backend.repositories.UserRepo;
import com.karot.food.backend.security.JwtAuthFilter;
import com.karot.food.backend.security.JwtUtil;
import com.karot.food.backend.service.interf.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailUtils emailUtils;
    private final VerificationUtils verificationUtils;


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
        log.info("User email: " + userDto.getEmail());
        String code = verificationUtils.generationCode(userDto.getEmail());
        emailUtils.sendVerificationMail(savedUser.getEmail(),"Activating your account",code);
        log.info("code: " + code);
        return Response.builder()
                .status(200)
                .message("User successfully added, please check your email for verify code.")
                .user(userDto)
                .build();
    }

    @Override
    public Response login(LoginRequest loginRequest) {
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
    public Response approve(String email, String code) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));
        if(!verificationUtils.verifyCode(user.getEmail(),code)){
            return Response.builder()
                    .status(400)
                    .message("Code expired.")
                    .build();
        }
        user.setApprove("true");
        userRepo.save(user);
        return Response.builder()
                .status(200)
                .message("User get approved, you can login now.")
                .build();
    }



}
