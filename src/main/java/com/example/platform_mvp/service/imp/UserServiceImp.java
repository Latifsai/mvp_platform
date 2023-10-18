package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.user.RegistrationAndUpdateUserRequest;
import com.example.platform_mvp.dto.user.UserRequestForUsers;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.repository.UserRepository;
import com.example.platform_mvp.service.UserService;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import com.example.platform_mvp.service.utilites.UserUtil;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    private final ServiceInterfaceImp serviceInterface;

    private final UserUtil util;

    private final ServiceUtil serviceUtil;

    @Override
    public UserRequestForUsers registrateUser(RegistrationAndUpdateUserRequest request) {
        User user = util.createUserFromRequest(request, repository.findAll());
        Service service = serviceInterface.saveService(request.getServiceTitle(), request.getMaxPriceOfService(),
                request.getMinPriceOfService(), request.getTypeOfService());
        user.setServices(List.of(service));

        repository.save(user);
        return util.convertToResponse(user, serviceUtil);
    }

    @Override
    public List<UserRequestForUsers> findUsersBySkill(String service) {
        List<String> allTypesOfServices = serviceUtil.getTypesValue();
        if (!allTypesOfServices.contains(service)) {
            throw new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, service));
        }

        List<User> users = filterUsersByService(repository.findAll(), service);
        return users.stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRequestForUsers> findUsersBySkillAndExperience(String service, Integer experience) {
        List<User> users = util.filterUserByExperience(repository.findAll(), experience);
        List<User> filteredUsers = filterUsersByService(users, service);
        return filteredUsers.stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserRequestForUsers> findUsersBySkillAndReputation(String service, Reputation reputation) {
        List<User> filteredByReputationUsers = repository.findAllByReputation(reputation);
        return filterUsersByService(filteredByReputationUsers, service).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserRequestForUsers> findUsersByJobTitle(String jobTitle) {
        return repository.findAllByJobTitle(jobTitle).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserRequestForUsers> findUsersByJobTitleAndExperience(String jobTitle, Integer experience) {
        return repository.findAllByJobTitleAndExperience(jobTitle, experience).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserRequestForUsers> findUsersByJobTitleAndReputation(String jobTitle, Reputation reputation) {
        return repository.findAllByJobTitleAndReputation(jobTitle, reputation).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserRequestForUsers> findAllUsers() {
        return repository.findAll().stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public UserRequestForUsers updateUser(RegistrationAndUpdateUserRequest request) {
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_USER_MESSAGE, request.getUsername())));

        User updatedUser = util.updateUser(user,request);
        if (util.checkCriteria(request.getServiceTitle())){
            user.setJobTitle(request.getServiceTitle());
            Service service = serviceInterface.findByTitle(updatedUser.getServices(), request.getServiceTitle());

            if (updatedUser.getServices().contains(service)) {
                 Service updatedService =  serviceUtil.updateService(service, request.getServiceTitle(), request.getMaxPriceOfService(),
                        request.getMinPriceOfService(), request.getTypeOfService());
                        serviceInterface.saveService(updatedService);
            }
        }

        repository.save(updatedUser);
        return util.convertToResponse(updatedUser, serviceUtil);
    }

    @Override
    public void deleteUserByUserName(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_USER_MESSAGE, username)));
        repository.delete(user);
    }

    private List<User> filterUsersByService(List<User> users, String service) {
        return users.stream()
                .filter(user -> user.getServices().stream()
                        .anyMatch(ser -> ser.getTypeOfService().toString().equals(service)))
                .toList();
    }
}
