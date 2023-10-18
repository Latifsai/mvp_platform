package com.example.platform_mvp.generator;

import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.enums.TypeOfService;

import java.math.BigDecimal;

public class EntityGenerator {

    public static Service getService() {
        return new Service(
                1L,
                "Software Developing",
                BigDecimal.valueOf(50000.00),
                BigDecimal.valueOf(4500.00),
                TypeOfService.IT
        );
    }

}
