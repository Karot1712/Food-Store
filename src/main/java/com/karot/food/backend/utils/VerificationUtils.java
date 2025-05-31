package com.karot.food.backend.utils;

import com.karot.food.backend.constants.FoodConstant;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationUtils {
    private static final String characters = FoodConstant.CHARACTERS;
    private static final int CODE_LENGTH = 6;
    private static final int expired_second = 60;
    private static final SecureRandom random = new SecureRandom();
    private final static Map<String, VerificationData> codeStorage= new HashMap<>();

    public static String generationRandomCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    public String generationCode(String userEmail){
        String code = generationRandomCode();
        LocalDateTime expiredTime = LocalDateTime.now().plusSeconds(expired_second);
        codeStorage.put(userEmail, new VerificationData(code,expiredTime));
        return code;
    }

    public boolean verifyCode(String userEmail, String code){
        VerificationData data = codeStorage.get(userEmail);
        if(data == null){
            return false; //No code found for user
        }
        if(LocalDateTime.now().isAfter(data.expiryTime)){
            codeStorage.remove(code);
            return false; // Code expired;
        }
        if(data.code.equals(code)){
            return true;
        }
        return false;
    }

    private static class VerificationData {
        String code;
        LocalDateTime expiryTime;

        public VerificationData(String code, LocalDateTime expiryTime) {
            this.code = code;
            this.expiryTime = expiryTime;
        }
    }
}
