package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
import com.example.platform_mvp.dto.service.UpdateServiceRequest;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.service.imp.ServiceInterfaceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceInterfaceImp service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse add(@Valid @RequestBody AddServiceRequest request) {
        return service.addService(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse update(@RequestBody UpdateServiceRequest request) {
        return service.updateService(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ServiceResponse> findAll() {
        return service.findAllServices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ServiceResponse findByID(@PathVariable(name = "id") Long id) {
        return service.findServiceByID(id);
    }

    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ServiceResponse> findByTypeOfService(@PathVariable(name = "type") TypeOfService type) {
        return service.findServicesByServiceType(type);
    }

    @GetMapping("/optimal/{type}/{price}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ServiceResponse> findByPriceAndTypeOfService(@PathVariable(name = "price") BigDecimal price,
                                                     @PathVariable(name = "type") TypeOfService type) {
        return service.findServicesByBelongsPriceAndType(price, type);
    }

    @GetMapping("/types")
    @ResponseStatus(HttpStatus.FOUND)
    public TypesOfServiceResponse findAllTypes() {
        return service.getAllTypesOfResponse();
    }
}
