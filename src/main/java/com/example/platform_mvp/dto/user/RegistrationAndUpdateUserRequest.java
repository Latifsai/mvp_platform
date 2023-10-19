package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.entities.enums.TypeOfService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RegistrationAndUpdateUserRequest {
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
}
