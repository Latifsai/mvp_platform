package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.service.AddAndUpdateServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.service.ServiceInterface;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceInterfaceImp implements ServiceInterface {

    private final ServiceUtil util;

    @Override
    public ServiceResponse addService(AddAndUpdateServiceRequest request) {
        return null;
    }

    @Override
    public ServiceResponse updateService(AddAndUpdateServiceRequest request) {
        return null;
    }

    @Override
    public List<ServiceResponse> findAllServices() {
        return null;
    }

    @Override
    public ServiceResponse findServiceByID(Long id) {
        return null;
    }

    @Override
    public ServiceResponse findServiceByServiceTitle(String serviceTitle) {
        return null;
    }

    @Override
    public List<ServiceResponse> findServicesByServiceType(TypeOfService typeOfService) {
        return null;
    }

    @Override
    public List<ServiceResponse> findServicesByBelongsPrice(BigDecimal price) {
        return null;
    }

    @Override
    public TypesOfServiceResponse getAllTypesOfResponse() {
        return util.getAllTypesOfServices();
    }
}
