//package com.example.platform_mvp.service.imp;
//
//import com.example.platform_mvp.dto.service.AddServiceRequest;
//import com.example.platform_mvp.dto.service.ServiceResponse;
//import com.example.platform_mvp.dto.service.TypesOfServiceResponse;
//import com.example.platform_mvp.dto.service.UpdateServiceRequest;
//import com.example.platform_mvp.entities.Service;
//import com.example.platform_mvp.entities.enums.TypeOfService;
//import com.example.platform_mvp.generator.DTOGenerator;
//import com.example.platform_mvp.generator.EntityGenerator;
//import com.example.platform_mvp.repository.ServiceRepository;
//import com.example.platform_mvp.service.utilites.ServiceUtil;
//import com.example.platform_mvp.validation.exceptions.NotFoundException;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ServiceInterfaceImpTest {
//
//    @Mock
//    private ServiceUtil util;
//
//    @Mock
//    private ServiceRepository repository;
//
//    @InjectMocks
//    private ServiceInterfaceImp serviceImp;
//
//    private final Service service = EntityGenerator.getService();
//    private final ServiceResponse response = DTOGenerator.getServiceResponse();
//
//    @Test
//    @DisplayName("Test addService method")
//    void addService() {
//        AddServiceRequest request = new AddServiceRequest("title", BigDecimal.TEN, BigDecimal.ONE, TypeOfService.IT);
//
//        when(util.getServiceFromRequest(request.getServiceTitle(), request.getMaxPrice(), request.getMinPrice(), request.getTypeOfService())).thenReturn(service);
//        when(repository.save(service)).thenReturn(service);
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(response, serviceImp.addService(request));
//        verify(util, times(1)).getServiceFromRequest(request.getServiceTitle(), request.getMaxPrice(), request.getMinPrice(), request.getTypeOfService());
//        verify(repository, times(1)).save(service);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test addService method RequestErrors")
//    void addServiceRequestErrors() {
//        AddServiceRequest request = new AddServiceRequest(" ", BigDecimal.ZERO, BigDecimal.ZERO, null);
//
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        var set = validator.validate(request);
//        assertEquals(4, set.size());
//    }
//
//    @Test
//    @DisplayName("Test updateService method")
//    void updateService() {
//        var request = UpdateServiceRequest.builder()
//                .id(1L)
//                .serviceTitle(" ")
//                .maxPrice(BigDecimal.ONE)
//                .minPrice(BigDecimal.ONE)
//                .typeOfService(null)
//                .build();
//
//        when(repository.findById(request.getId())).thenReturn(Optional.of(service));
//
//        service.setMinPrice(BigDecimal.TEN);
//        service.setMaxPrice(BigDecimal.ZERO);
//
//        when(util.updateService(service, request.getServiceTitle(), request.getMaxPrice(), request.getMinPrice(), request.getTypeOfService())).thenReturn(service);
//        when(repository.save(service)).thenReturn(service);
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(response, serviceImp.updateService(request));
//        verify(repository, times(1)).findById(request.getId());
//        verify(util, times(1)).updateService(service, request.getServiceTitle(), request.getMaxPrice(), request.getMinPrice(), request.getTypeOfService());
//        verify(repository, times(1)).save(service);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test updateService method Exception")
//    void updateServiceException() {
//        var request = UpdateServiceRequest.builder()
//                .id(1L)
//                .serviceTitle(" ")
//                .maxPrice(BigDecimal.ONE)
//                .minPrice(BigDecimal.ONE)
//                .typeOfService(null)
//                .build();
//
//        when(repository.findById(request.getId())).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> serviceImp.updateService(request));
//        verify(repository, times(1)).findById(request.getId());
//    }
//
//    @Test
//    @DisplayName("Test findAllServices method")
//    void findAllServices() {
//        var services = Collections.singletonList(service);
//
//        when(repository.findAll()).thenReturn(services);
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(Collections.singletonList(response), serviceImp.findAllServices());
//        verify(repository, times(1)).findAll();
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test findServiceByID method")
//    void findServiceByID() {
//        when(repository.findById(1L)).thenReturn(Optional.of(service));
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(response, serviceImp.findServiceByID(1L));
//        verify(repository, times(1)).findById(1L);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test findServiceByID method throws Exception")
//    void findServiceByIDException() {
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> serviceImp.findServiceByID(1L));
//        verify(repository, times(1)).findById(1L);
//    }
//
//    @Test
//    @DisplayName("Test findServiceByServiceTitle method")
//    void findServiceByServiceTitle() {
//        String title = "title";
//
//        when(repository.searchByServiceTitle(title)).thenReturn(Optional.of(service));
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(response, serviceImp.findServiceByServiceTitle(title));
//        verify(repository, times(1)).searchByServiceTitle(title);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test findServiceByServiceTitle method throws Exception")
//    void findServiceByServiceTitleException() {
//        String title = "title";
//
//        when(repository.searchByServiceTitle(title)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> serviceImp.findServiceByServiceTitle(title));
//        verify(repository, times(1)).searchByServiceTitle(title);
//    }
//
//    @Test
//    @DisplayName("Test findServicesByServiceType method")
//    void findServicesByServiceType() {
//        String type = "IT";
//
//        when(util.getTypesValue()).thenReturn(Collections.singletonList(type));
//        when(repository.findAllByTypeOfService(TypeOfService.IT)).thenReturn(Collections.singletonList(service));
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(Collections.singletonList(response), serviceImp.findServicesByServiceType(type));
//        verify(util, times(1)).getTypesValue();
//        verify(repository, times(1)).findAllByTypeOfService(TypeOfService.IT);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test findServicesByBelongsPriceAndType method")
//    void findServicesByBelongsPriceAndType() {
//        BigDecimal price = BigDecimal.TEN;
//        String type = "type";
//        var services = Collections.singletonList(service);
//        var responses = Collections.singletonList(response);
//
//        when(repository.findAll()).thenReturn(services);
//        when(util.getAllServicesWithSuitableSum(services, price, type)).thenReturn(services);
//        when(util.convertToResponse(service)).thenReturn(response);
//
//        assertEquals(responses, serviceImp.findServicesByBelongsPriceAndType(price, type));
//        verify(repository, times(1)).findAll();
//        verify(util, times(1)).getAllServicesWithSuitableSum(services, price, type);
//        verify(util, times(1)).convertToResponse(service);
//    }
//
//    @Test
//    @DisplayName("Test getAllTypesOfResponse method")
//    void getAllTypesOfResponse() {
//        Map<TypeOfService, String> map = new HashMap<>();
//        map.put(TypeOfService.IT, "IT service");
//
//        var response = TypesOfServiceResponse.builder()
//                .description(map)
//                .build();
//
//        when(util.getAllTypesOfServices()).thenReturn(response);
//
//        assertEquals(response, serviceImp.getAllTypesOfResponse());
//
//    }
//}