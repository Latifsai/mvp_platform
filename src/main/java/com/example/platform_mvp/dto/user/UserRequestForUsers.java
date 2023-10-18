package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.entities.enums.Reputation;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserRequestForUsers {
    String username;
    String firstName;
    String surname;
    String jobTitle;
    Integer experience;
    String informationAboutUser;
    Reputation reputation;
    List<ServiceResponse> services;
}
