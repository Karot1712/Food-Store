package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.MailBody;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.model.ForgotPassword;
import com.karot.food.backend.model.User;
import com.karot.food.backend.repositories.ForgotPasswordRepo;
import com.karot.food.backend.repositories.UserRepo;
import com.karot.food.backend.service.EmailService;
import com.karot.food.backend.service.interf.ForgotPasswordService;
import com.karot.food.backend.utils.ChangePassword;
import com.karot.food.backend.utils.VerificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    private final ForgotPasswordRepo forgotPasswordRepo;
    private final UserRepo userRepo;
    private final VerificationUtils verificationUtils;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response verifyMail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
        String otp = verificationUtils.generationCode(email);
        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("OTP for your forgot password request")
                .text("This is the OTP for your forgot password request: " + otp)
                .build();
        log.info(mailBody.to());
        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .user(user)
                .expirationTime(new Date(System.currentTimeMillis() + 60 * 1000)) // 1 min
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepo.save(fp);

        return Response.builder()
                .status(200)
                .message("Check your mail for the OTP")
                .build();
    }

    @Override
    public Response verifyOtp(String otp, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User not found"));
        ForgotPassword fp = forgotPasswordRepo.findByOtpAndUser(otp,user)
                .orElseThrow(()->new RuntimeException("Invalid OTP for email."));
        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepo.deleteById(fp.getId());
            return Response.builder()
                    .status(400)
                    .message("OTP has expired")
                    .build();
        }

        return Response.builder()
                .status(200)
                .message("OTP verified")
                .build();
    }

    @Override
    public Response changePassword(String email, ChangePassword changePassword) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));
        if(!Objects.equals(changePassword.newPassword(),changePassword.repeatPassword())){
            return Response.builder()
                    .status(400)
                    .message("Please enter your password again")
                    .build();

        } else if (passwordEncoder.matches(changePassword.newPassword(), user.getPassword())) {
            return Response.builder()
                    .status(400)
                    .message("New password must not be identical to the old password.")
                    .build();
        } else {
            user.setPassword(passwordEncoder.encode(changePassword.newPassword()));
        }
        userRepo.save(user);

        return Response.builder()
                .status(200)
                .message("Password has been changed")
                .build();
    }
}
