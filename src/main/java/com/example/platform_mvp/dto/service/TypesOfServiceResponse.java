package com.example.platform_mvp.dto.service;

import com.example.platform_mvp.entities.enums.TypeOfService;
import lombok.Data;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Data
public class TypesOfServiceResponse {
    private Map<TypeOfService, String> description = new HashMap<>();

}
