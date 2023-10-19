package com.example.platform_mvp.dto.searchNeed;

import com.example.platform_mvp.entities.enums.Reputation;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class SearchNeedsResponse {
    String searchLabels;
    BigDecimal price;
    Integer experience;
    Reputation reputation;
}
