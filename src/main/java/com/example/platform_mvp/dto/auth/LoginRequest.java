package com.example.platform_mvp.dto.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginRequest {
    String username;
    String password;
}
