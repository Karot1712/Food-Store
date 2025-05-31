package com.karot.food.backend.controller.interf;

import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.utils.ChangePassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/forgot-password")
public interface ForgotPasswordController {
    //send mail for verification
    @PostMapping(path = "/verify-mail/{email}")
    public ResponseEntity<Response> verifyMail(@PathVariable String email);

    @PostMapping(path = "/verify-otp/{email}/{otp}")
    public ResponseEntity<Response> verifyOtp(@PathVariable String otp,
                                              @PathVariable String email
    );

    @PostMapping(path = "/change-password/{email}")
    public ResponseEntity<Response>changePassword(@PathVariable String email,
                                                  @RequestBody ChangePassword changePassword
    );

}
