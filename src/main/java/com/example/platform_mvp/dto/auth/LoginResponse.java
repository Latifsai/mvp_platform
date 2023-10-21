package com.example.platform_mvp.dto.auth;

import com.example.platform_mvp.dto.user.UserResponseForUsers;
import lombok.Value;

import java.util.List;

@Value
public class LoginResponse {
    String token;
    List<UserResponseForUsers> users;
}
