package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.user.RegistrationAndUpdateUserRequest;
import com.example.platform_mvp.dto.user.UserRequestForUsers;
import com.example.platform_mvp.entities.enums.Reputation;

import java.util.List;

public interface UserService {
    UserRequestForUsers registrateUser(RegistrationAndUpdateUserRequest registrationAndUpdateUserRequest);
    List<UserRequestForUsers> findUsersBySkill(String service);
    List<UserRequestForUsers> findUsersBySkillAndExperience(String service, Integer experience);
    List<UserRequestForUsers> findUsersBySkillAndReputation(String service, Reputation reputation);
    List<UserRequestForUsers> findUsersByJobTitle(String jobTitle);
    List<UserRequestForUsers> findUsersByJobTitleAndExperience(String jobTitle, Integer experience);
    List<UserRequestForUsers> findUsersByJobTitleAndReputation(String jobTitle, Reputation reputation);
    List<UserRequestForUsers> findAllUsers();
    UserRequestForUsers updateUser(RegistrationAndUpdateUserRequest registrationAndUpdateUserRequest);
    void deleteUserByUserName(String username);






}
