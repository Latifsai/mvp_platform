package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.dto.service.UpdateServiceRequest;
import com.example.platform_mvp.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceInterface {
    ServiceResponse addService(AddServiceRequest request, User user);
    ServiceResponse updateService(UpdateServiceRequest request);
    List<ServiceResponse> findAllServices();
    ServiceResponse findServiceByID(Long id);
    ServiceResponse findServiceByServiceTitle(String serviceTitle);
    List<ServiceResponse> findServicesByServiceType(String typeOfService);
    List<ServiceResponse> findServicesByBelongsPriceAndType(BigDecimal price, String typeOfService);
    TypesOfServiceResponse getAllTypesOfResponse();
}
