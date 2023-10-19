package com.example.platform_mvp.dto.searchNeed;

import com.example.platform_mvp.entities.enums.Reputation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AddNeedsRequest {
    @NotBlank(message = "username must not be blank!")
    String username;
    @NotBlank(message = "searchLabels must not be blank!")
    String searchLabels;
    @Positive(message = "price must be positive")
    BigDecimal price;
    @Positive(message = "experience must be positive")
    Integer experience;
    @NotNull(message = "searchLabels must not be null!")
    Reputation reputation;
}
