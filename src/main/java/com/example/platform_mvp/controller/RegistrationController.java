package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegistrationController {

    private final UserServiceImp service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseForUsers registration(@Valid @RequestBody RegistrationUserRequest request) {
        return service.registrateUser(request);
    }
}
