package com.example.platform_mvp.dto.service;

import com.example.platform_mvp.entities.enums.TypeOfService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class AddServiceRequest {
    @NotBlank(message = "username must not be blank!")
    String username;
    @NotBlank(message = "serviceTitle must not be blank!")
    String serviceTitle;
    @Positive(message = "maxPrice must not be 0 or less")
    BigDecimal maxPrice;
    @Positive(message = "minPrice must not be 0 or less")
    BigDecimal minPrice;
    @NotNull(message = "typeOfService must not be null")
    TypeOfService typeOfService;
}
