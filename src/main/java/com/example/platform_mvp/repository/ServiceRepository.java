package com.example.platform_mvp.repository;

import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.enums.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> searchByServiceTitle(String title);
    List<Service> findAllByTypeOfService(TypeOfService typeOfService);
}
