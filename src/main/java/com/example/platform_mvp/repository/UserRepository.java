package com.example.platform_mvp.repository;

import com.example.platform_mvp.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
