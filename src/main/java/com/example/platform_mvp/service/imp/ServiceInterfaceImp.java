package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.dto.service.UpdateServiceRequest;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.repository.ServiceRepository;
import com.example.platform_mvp.service.ServiceInterface;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceInterfaceImp implements ServiceInterface {

    private final ServiceUtil util;

    private final ServiceRepository repository;


    @Override
    public ServiceResponse addService(AddServiceRequest request, User user) {
        Service service = util.getServiceFromRequest(request.getServiceTitle(), request.getMaxPrice(),
                request.getMinPrice(), request.getTypeOfService());

        user.getServices().add(service);
        service.setUser(user);
        repository.save(service);
        return util.convertToResponse(service);
    }

    @Override
    public ServiceResponse updateService(UpdateServiceRequest request) {
        Service service = findByID(request.getId());
        Service updatedService = util.updateService(service, request.getServiceTitle(), request.getMaxPrice(),
                request.getMinPrice(), request.getTypeOfService());

        repository.save(updatedService);
        return util.convertToResponse(updatedService);
    }

    @Override
    public List<ServiceResponse> findAllServices() {
        return repository.findAll().stream()
                .map(util::convertToResponse)
                .toList();
    }

    @Override
    public ServiceResponse findServiceByID(Long id) {
        return util.convertToResponse(findByID(id));
    }

    @Override
    public ServiceResponse findServiceByServiceTitle(String serviceTitle) {
        return util.convertToResponse(repository.searchByServiceTitle(serviceTitle)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, serviceTitle))));
    }

    @Override
    public List<ServiceResponse> findServicesByServiceType(String typeOfService) {
        return repository.findAllByTypeOfService(checkType(typeOfService)).stream()
                .map(util::convertToResponse)
                .toList();
    }

    @Override
    public List<ServiceResponse> findServicesByBelongsPriceAndType(BigDecimal price, String typeOfService) {
        List<Service> services = repository.findAll();
        return util.getAllServicesWithSuitableSum(services, price, typeOfService).stream()
                .map(util::convertToResponse)
                .toList();
    }

    @Override
    public TypesOfServiceResponse getAllTypesOfResponse() {
        return util.getAllTypesOfServices();
    }

    public Service addServiceToUser(String title, BigDecimal maxPrice, BigDecimal minPrice, TypeOfService type, User user) {
        Service service = util.getServiceFromRequest(title, maxPrice, minPrice, type);
        service.setUser(user);
        return repository.save(service);
    }

    public Service findByTitle(List<Service> services, String title) {
        return services.stream()
                .filter(service -> service.getServiceTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, title)));
    }

    public List<ServiceResponse> getAllServicesBelongsUser(User user) {
        return repository.findAllByUser(user).stream()
                .map(util::convertToResponse)
                .toList();
    }

    public void addServiceToUser(Service service) {
        repository.save(service);
    }

    private Service findByID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, id)));
    }

    private TypeOfService checkType(String type) {
        if (util.getTypesValue().contains(type)) {
            return Enum.valueOf(TypeOfService.class, type);
        }
        throw new NotFoundException(String.format(ExceptionMessage.DOES_NOT_EXIST_TYPE_MESSAGE, type));
    }



}

