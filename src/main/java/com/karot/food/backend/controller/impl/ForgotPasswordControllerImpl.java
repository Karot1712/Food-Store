package com.karot.food.backend.controller.impl;

import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.controller.interf.ForgotPasswordController;
import com.karot.food.backend.service.interf.ForgotPasswordService;
import com.karot.food.backend.utils.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForgotPasswordControllerImpl implements ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @Override
    public ResponseEntity<Response> verifyMail(String email) {
        return ResponseEntity.ok(forgotPasswordService.verifyMail(email));
    }

    @Override
    public ResponseEntity<Response> verifyOtp(String otp, String email) {
        return ResponseEntity.ok(forgotPasswordService.verifyOtp(otp,email));
    }

    @Override
    public ResponseEntity<Response> changePassword(String email, ChangePassword changePassword) {
        return ResponseEntity.ok(forgotPasswordService.changePassword(email,changePassword));
    }
}
