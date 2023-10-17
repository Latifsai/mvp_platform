package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.enums.TypeOfService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.platform_mvp.entities.enums.TypeOfService.*;

@Component
public class ServiceUtil {

    public TypesOfServiceResponse getAllTypesOfServices() {
        TypesOfServiceResponse response = new TypesOfServiceResponse();
        response.setDescription(fillMap());
        return response;
    }

    public Service getServiceFromRequest(AddServiceRequest request) {
        Service service = new Service();
        service.setServiceTitle(request.getServiceTitle());
        service.setMaxPrice(request.getMaxPrice());
        service.setMinPrice(request.getMinPrice());
        service.setTypeOfService(request.getTypeOfService());
        return service;
    }

    public ServiceResponse convertToResponse(Service service) {
        return ServiceResponse.builder()
                .serviceTitle(service.getServiceTitle())
                .maxPrice(service.getMaxPrice())
                .minPrice(service.getMinPrice())
                .typeOfService(service.getTypeOfService())
                .build();
    }

    private Map<TypeOfService, String> fillMap() {
        Map<TypeOfService, String> map = new HashMap<>();
        map.put(Legal, "legal services, lawyer or notary");
        map.put(Accounting, "sphere in areas of Accounting, invoice settlements, etc.");
        map.put(IT, "IT services sector");
        map.put(Construction, "Services of construction companies");
        map.put(Housing_and_communal, "services in the field of housing and communal services");
        map.put(Trade, "trade services sector");
        map.put(Financial, "financial services industry");
        map.put(Cosmetic, "beauty sphere");
        map.put(Educational, "education sector");
        map.put(Delivery, "scope of Security provision");
        map.put(Tourist, "sphere of tourism provision");
        map.put(Entertainment_and_recreation, "entertainment and recreation services");
        map.put(Medical_and_sanatorium, "Medical and Sanatorium services");
        return map;
    }
}
