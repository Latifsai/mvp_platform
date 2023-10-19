package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class UpdateUserRequest {
    String username;
    String firstName;
    String surname;
    String password;
    String firmaTitle;
    Integer experience;
    String userInfo;
    String oldServiceTitle;
    String newServiceTitle;
    BigDecimal maxPriceOfService;
    BigDecimal minPriceOfService;
    TypeOfService typeOfService;
}
