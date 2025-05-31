package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.utils.ChangePassword;

public interface ForgotPasswordService {
    Response verifyMail(String email);
    Response verifyOtp(String otp,String email);
    Response changePassword(String email, ChangePassword changePassword);
}
