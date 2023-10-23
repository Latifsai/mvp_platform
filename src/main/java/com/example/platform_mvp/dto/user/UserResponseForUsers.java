package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.entities.enums.Reputation;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class UserResponseForUsers {
    String username;
    String firstName;
    String surname;
    String firmaTitle;
    Integer experience;
    String informationAboutUser;
    String uniqueNumber;
    Integer credits;
    String email;
    Reputation reputation;
    List<ServiceResponse> services;
    String labels;
    BigDecimal wantedPrice;
    Integer wantedExperience;
    Reputation wandetReputation;

}
