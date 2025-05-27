package com.karot.food.backend.service.impl;

import com.google.common.base.Strings;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.Utils.EmailUtils;
import com.karot.food.backend.Utils.VerificationUtils;
import com.karot.food.backend.security.JwtAuthFilter;
import com.karot.food.backend.security.JwtUtil;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.User;
import com.karot.food.backend.repositories.UserRepo;
import com.karot.food.backend.service.interf.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JwtAuthFilter jwtFilter;
    private EmailUtils emailUtils;
    private final VerificationUtils verificationUtils;

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
                emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approve","USER:- " + user + "\n is approve by \nADMIN:- " + jwtFilter.getCurrentUser(),admin);
            } else {
                emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disable","USER:- " + user + "\n is disable by \nADMIN:- " + jwtFilter.getCurrentUser(),admin);
            }
        }catch (Exception e){
            log.error("e: ", e);
        }

    }

    @Override
    public Response changePassword(String oldPassword, String password) {
        User user = userRepo.findByEmail(jwtFilter.getCurrentUser()).orElseThrow(()-> new NotFoundException("User not found"));
        //check the old password
        if(passwordEncoder.matches(oldPassword, user.getPassword())){
            return Response.builder()
                    .status(400)
                    .message("Incorrect old password.")
                    .build();
        } else {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepo.save(user);
        return Response.builder()
                .status(200)
                .message("Password updated successfully")
                .build();
    }

    @Override
    public Response forgotPassword(String email) {
        log.info("mail: {}" , email);
        User user = userRepo.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
        String code = verificationUtils.generationCode(email);
        if (!Objects.isNull(user.getEmail()) && !Strings.isNullOrEmpty(user.getEmail())) {
            try {
                emailUtils.forgetMail(user.getEmail(),"Get verification code", code);
            } catch (MessagingException e) {
                log.info("error: ",e);
                throw new RuntimeException(e);
            }
        }

        return Response.builder()
                .message("Check mail for Credentials.")
                .build();
    }
}

