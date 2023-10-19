package com.example.platform_mvp.repository;

import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchNeedRepository extends JpaRepository<SearchNeed, Long> {
}
