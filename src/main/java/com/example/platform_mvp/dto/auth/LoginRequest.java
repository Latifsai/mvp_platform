package com.example.platform_mvp.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginRequest {
    @NotBlank(message = "username must not be blank!")
    String username;
    @NotBlank(message = "password must not be blank!")
    String password;
}
