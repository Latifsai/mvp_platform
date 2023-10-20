package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.enums.Reputation;

import java.util.List;

public interface UserService {
    UserResponseForUsers registrateUser(RegistrationUserRequest registrationUserRequest);
    List<UserResponseForUsers> findUsersBySkill(String service);
    List<UserResponseForUsers> findUsersBySkillAndExperience(String service, Integer experience);
    List<UserResponseForUsers> findUsersBySkillAndReputation(String service, Reputation reputation);
    List<UserResponseForUsers> findUsersByFirmaTitle(String jobTitle);
    List<UserResponseForUsers> findUsersByServiceTitleAndExperience(String jobTitle, Integer experience);
    List<UserResponseForUsers> findUsersByServiceTitleAndReputation(String jobTitle, Reputation reputation);
    List<UserResponseForUsers> findAllUsers();
    UserResponseForUsers updateUser(UpdateUserRequest request);
    void deleteUserByUserName(String username);






}
