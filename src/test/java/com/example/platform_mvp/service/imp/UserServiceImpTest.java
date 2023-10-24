//package com.example.platform_mvp.service.imp;
//
//import com.example.platform_mvp.entities.User;
//import com.example.platform_mvp.generator.EntityGenerator;
//import com.example.platform_mvp.repository.UserRepository;
//import com.example.platform_mvp.service.utilites.ServiceUtil;
//import com.example.platform_mvp.service.utilites.UserUtil;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//@FieldDefaults(level = AccessLevel.PRIVATE)
//class UserServiceImpTest {
//    @Mock
//    UserRepository repository;
//
//    @Mock
//    ServiceInterfaceImp serviceInterface;
//
//    @Mock
//    UserUtil util;
//
//    @Mock
//    ServiceUtil serviceUtil;
//
//    @InjectMocks
//    UserServiceImp userService;
//
//    final User user = EntityGenerator.getUser();
//
//    @Test
//    @DisplayName("Test registrateUser method")
//    void registrateUser() {
//
//    }
//
//    @Test
//    void findUsersBySkill() {
//    }
//
//    @Test
//    void findUsersBySkillAndExperience() {
//    }
//
//    @Test
//    void findUsersBySkillAndReputation() {
//    }
//
//    @Test
//    void findUsersByJobTitle() {
//    }
//
//    @Test
//    void findUsersByJobTitleAndExperience() {
//    }
//
//    @Test
//    void findUsersByJobTitleAndReputation() {
//    }
//
//    @Test
//    void findAllUsers() {
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void deleteUserByUserName() {
//    }
//}