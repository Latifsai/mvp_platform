package com.example.platform_mvp.dto.service;

import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class TypesOfServiceResponse {
    private Map<TypeOfService, String> description;

}
