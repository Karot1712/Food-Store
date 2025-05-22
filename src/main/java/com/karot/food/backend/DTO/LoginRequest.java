package com.karot.food.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {
    @NotBlank(message = "Need email")
    private String email;

    @NotBlank(message = "Need password")
    private String password;
}
