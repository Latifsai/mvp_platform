package com.example.platform_mvp.dto.service;

import com.example.platform_mvp.entities.enums.TypeOfService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AddServiceRequest {
    @NotBlank(message = "serviceTitle must not be blank!")
    String serviceTitle;
    @PositiveOrZero(message = "maxPrice must not be 0 or less")
    BigDecimal maxPrice;
    @PositiveOrZero(message = "minPrice must not be 0 or less")
    BigDecimal minPrice;
    @NotNull(message = "typeOfService must not be null")
    TypeOfService typeOfService;
}
