package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.dto.user.UserRequestForUsers;
import com.example.platform_mvp.entities.enums.Reputation;

import java.util.List;

public interface UserService {
    UserRequestForUsers registrateUser(RegistrationUserRequest registrationUserRequest);
    List<UserRequestForUsers> findUsersBySkill(String service);
    List<UserRequestForUsers> findUsersBySkillAndExperience(String service, Integer experience);
    List<UserRequestForUsers> findUsersBySkillAndReputation(String service, Reputation reputation);
    List<UserRequestForUsers> findUsersByFirmaTitle(String jobTitle);
    List<UserRequestForUsers> findUsersByServiceTitleAndExperience(String jobTitle, Integer experience);
    List<UserRequestForUsers> findUsersByServiceTitleAndReputation(String jobTitle, Reputation reputation);
    List<UserRequestForUsers> findAllUsers();
    UserRequestForUsers updateUser(UpdateUserRequest request);
    void deleteUserByUserName(String username);






}
