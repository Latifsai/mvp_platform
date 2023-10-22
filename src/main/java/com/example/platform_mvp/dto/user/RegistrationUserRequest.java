package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.entities.enums.TypeOfService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RegistrationUserRequest {
    @NotBlank(message = "username must nto be null blank!")
    String username;
    @NotBlank(message = "firstName must nto be null blank!")
    String firstName;
    @NotBlank(message = "surname must nto be null blank!")
    String surname;
    @Email
    String email;
    @NotBlank(message = "firmaTitle must nto be null blank!")
    String firmaTitle;
    Integer experience;
    @NotBlank(message = "userInfo must nto be null blank!")
    String userInfo;

    //to create service
    @NotBlank(message = "serviceTitle must nto be null blank!")
    String serviceTitle;
    BigDecimal maxPriceOfService;
    BigDecimal minPriceOfService;
    TypeOfService typeOfService;

    // to create searchNeed
    String labels;
    BigDecimal price;
    Integer experienceOfUserToFind;
    Reputation reputationOfUserToFind;
}
