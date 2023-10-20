package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImp service;

    @PostMapping("/new_service")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse add(@RequestBody AddServiceRequest request) {
        return service.addNewServiceToUser(request);
    }

    @GetMapping("/skills/{skill}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findBySkills(@PathVariable(name = "skill") String skill) {
        return service.findUsersBySkill(skill);
    }

    @GetMapping("/skills_and_experience/{skill}/{experience}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findBySkills(@PathVariable(name = "skill") String skill,
                                                   @PathVariable(name = "experience") Integer experience) {
        return service.findUsersBySkillAndExperience(skill, experience);
    }

    @GetMapping("/skills_and_reputation/{skill}/{reputation}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findBySkillsAndReputation(@PathVariable(name = "skill") String skill,
                                                                @PathVariable(name = "reputation") Reputation reputation) {
        return service.findUsersBySkillAndReputation(skill, reputation);
    }

    @GetMapping("/firma_title/{firmaTitle}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findByJobTitle(@PathVariable(name = "firmaTitle") String firmaTitle) {
        return service.findUsersByFirmaTitle(firmaTitle);
    }

    @GetMapping("/service_title_and_experience/{serviceTitle}/{experience}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findByServiceTitleAndExperience(@PathVariable(name = "serviceTitle") String serviceTitle,
                                                                      @PathVariable(name = "experience") Integer experience) {
        return service.findUsersByServiceTitleAndExperience(serviceTitle, experience);
    }

    @GetMapping("/service_title_and_reputation/{serviceTitle}/{reputation}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findByServiceTitleAndReputation(@PathVariable(name = "serviceTitle") String serviceTitle,
                                                                      @PathVariable(name = "reputation") Reputation reputation) {
        return service.findUsersByServiceTitleAndReputation(serviceTitle, reputation);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findByAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/services_user/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ServiceResponse> getAllServicesBelongsUser(@PathVariable(name = "username") String username) {
        return service.getAllServicesBelongsUser(username);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseForUsers update(@RequestBody UpdateUserRequest request) {
        return service.updateUser(request);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "username") String username) {
        service.deleteUserByUserName(username);
    }



}
