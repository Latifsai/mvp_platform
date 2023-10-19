package com.example.platform_mvp.dto.user;

import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AddNewServiceToUserRequest {
    String username;
    String serviceTitle;
    BigDecimal maxPrice;
    BigDecimal minPrice;
    TypeOfService type;

}
