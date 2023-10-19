package com.example.platform_mvp.repository;

import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByReputation(Reputation reputation);
    List<User> findAllByFirmaTitle(String firmaTitle);
    List<User> findAllByExperience(Integer experience);
    Optional<User> findByUsername(String username);
}
