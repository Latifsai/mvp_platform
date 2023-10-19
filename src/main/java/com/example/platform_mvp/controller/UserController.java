package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.user.RegistrationAndUpdateUserRequest;
import com.example.platform_mvp.dto.user.UserRequestForUsers;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.service.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImp service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequestForUsers register(@RequestBody RegistrationAndUpdateUserRequest request) {
        return service.registrateUser(request);
    }

    @GetMapping("/skills/{skill}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findBySkills(@PathVariable(name = "skill") String skill) {
        return service.findUsersBySkill(skill);
    }

    @GetMapping("/skills_and_experience/{skill}/{experience}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findBySkills(@PathVariable(name = "skill") String skill,
                                                  @PathVariable(name = "experience") Integer experience) {
        return service.findUsersBySkillAndExperience(skill, experience);
    }

    @GetMapping("/skills_and_reputation/{skill}/{reputation}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findBySkillsAndReputation(@PathVariable(name = "skill") String skill,
                                                               @PathVariable(name = "reputation") Reputation reputation) {
        return service.findUsersBySkillAndReputation(skill, reputation);
    }

    @GetMapping("/job_title/{jobTitle}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findByJobTitle(@PathVariable(name = "jobTitle") String jobTitle) {
        return service.findUsersByJobTitle(jobTitle);
    }

    @GetMapping("/job_title_and_experience/{jobTitle}/{experience}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findByJobTitleAndExperience(@PathVariable(name = "jobTitle") String jobTitle,
                                                                 @PathVariable(name = "experience") Integer experience) {
        return service.findUsersByJobTitleAndExperience(jobTitle, experience);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserRequestForUsers> findByAllUsers() {
        return service.findAllUsers();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequestForUsers update(@RequestBody RegistrationAndUpdateUserRequest request) {
        return service.updateUser(request);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "username") String username) {
        service.deleteUserByUserName(username);
    }
}
