package com.example.platform_mvp.repository;

import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByReputation(Reputation reputation);
    List<User> findAllByJobTitle(String jobTitle);
    List<User> findAllByJobTitleAndExperience(String jobTitle, Integer experience);
    List<User> findAllByJobTitleAndReputation(String jobTitle, Reputation reputation);
    Optional<User> findByUsername(String username);
}
