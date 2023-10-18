package com.example.platform_mvp.generator;

import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.entities.enums.TypeOfService;

import java.math.BigDecimal;

public class DTOGenerator {
    public static ServiceResponse getServiceResponse() {
        return ServiceResponse.builder()
                .serviceTitle("Software Developing")
                .maxPrice(BigDecimal.valueOf(50000.00))
                .minPrice(BigDecimal.valueOf(4500.00))
                .typeOfService(TypeOfService.IT)
                .build();
    }
}
