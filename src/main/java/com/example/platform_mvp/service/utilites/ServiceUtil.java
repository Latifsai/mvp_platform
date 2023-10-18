package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.dto.service.UpdateServiceRequest;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.enums.TypeOfService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.platform_mvp.entities.enums.TypeOfService.*;

@Component
public class ServiceUtil {

    public TypesOfServiceResponse getAllTypesOfServices() {
        return TypesOfServiceResponse.builder()
                .description(fillMap())
                .build();
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

    public Service updateService(Service service, UpdateServiceRequest request) {
        if (!request.getServiceTitle().isBlank()) service.setServiceTitle(request.getServiceTitle());
        if (request.getMaxPrice() != null || !request.getMaxPrice().equals(BigDecimal.ZERO))
            service.setMaxPrice(request.getMaxPrice());
        if (request.getMinPrice() != null || !request.getMinPrice().equals(BigDecimal.ZERO))
            service.setMinPrice(request.getMinPrice());
        if (request.getTypeOfService() != null) service.setTypeOfService(request.getTypeOfService());
        return service;
    }

    //remake this method
    public List<Service> getAllServicesWithSuitableSum(List<Service> services, BigDecimal price, String type) {
        BigDecimal changer = BigDecimal.valueOf(500L);
        List<Service> list = new ArrayList<>();

        List<Service> sortedServices = services.stream()
                .filter(service -> service.getTypeOfService().toString().equals(type))
                .toList();

        for (Service s : sortedServices) {
            if ((price.compareTo(s.getMaxPrice()) >= 0 || price.compareTo(s.getMinPrice()) >= 0)
                    || (s.getMaxPrice().compareTo(price.subtract(changer)) <= 0 || s.getMinPrice().compareTo(price.subtract(changer)) <= 0)
                    || (s.getMinPrice().compareTo(price.add(changer)) <= 0 || s.getMaxPrice().compareTo(price.add(changer)) <= 0)) {
                list.add(s);
            }
        }
        return list;
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

    public List<String> getEnumsValue() {
        return fillMap().keySet().stream()
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

}
