package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.entities.enums.TypeOfService;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceInterface {
    ServiceResponse addService(AddServiceRequest request);
    ServiceResponse updateService(AddServiceRequest request);
    List<ServiceResponse> findAllServices();
    ServiceResponse findServiceByID(Long id);
    ServiceResponse findServiceByServiceTitle(String serviceTitle);
    List<ServiceResponse> findServicesByServiceType(TypeOfService typeOfService);
    List<ServiceResponse> findServicesByBelongsPrice(BigDecimal price);
    TypesOfServiceResponse getAllTypesOfResponse();
}
