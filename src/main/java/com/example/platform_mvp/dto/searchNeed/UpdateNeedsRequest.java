package com.example.platform_mvp.dto.searchNeed;

import com.example.platform_mvp.entities.enums.Reputation;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UpdateNeedsRequest {
    String username;
    String searchLabels;
    BigDecimal price;
    Integer experience;
    Reputation reputation;
}
