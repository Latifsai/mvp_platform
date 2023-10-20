package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RegistrationUserRequest {
    String username;
    String firstName;
    String surname;
    String password;
    String firmaTitle;
    Integer experience;
    String userInfo;
    String serviceTitle;
    BigDecimal maxPriceOfService;
    BigDecimal minPriceOfService;
    TypeOfService typeOfService;
    String labels;
    BigDecimal price;
    Integer experienceOfUserToFind;
    Reputation reputation;
}
