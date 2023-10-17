package com.example.platform_mvp.dto.service;

import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ServiceResponse {
    String serviceTitle;
    BigDecimal maxPrice;
    BigDecimal minPrice;
    TypeOfService typeOfService;
}
