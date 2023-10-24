package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.service.AddServiceRequest;
import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.repository.UserRepository;
import com.example.platform_mvp.service.GMailer;
import com.example.platform_mvp.service.SearchNeedService;
import com.example.platform_mvp.service.UserService;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import com.example.platform_mvp.service.utilites.UserUtil;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final SearchNeedService searchNeedService;

    private final PasswordEncoder passwordEncoder;

    private final GMailer gMailer;

    @Override
    public UserResponseForUsers registrateUser(RegistrationUserRequest request) {
        User user = util.createUserFromRequest(request, repository.findAll());
        String password = util.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        gMailer.sendMail("Invite message", user.getEmail(), user.getFirstName(), user.getUsername(), password);

        user.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROliboE48ewE7c1ysaJ-ejMSkQ8UFxt3qEvw&usqp=CAU");
        repository.save(user);

        Service service = serviceInterface.addServiceToUser(request.getServiceTitle(), request.getMaxPriceOfService(),
                request.getMinPriceOfService(), request.getTypeOfService(), user);

        SearchNeed need = searchNeedService.addNeedsToUser(request.getLabels(), request.getPrice(),
                request.getExperienceOfUserToFind(), request.getReputationOfUserToFind(), user);

        user.setServices(List.of(service));
        user.setSearchNeed(need);
        need.setUser(user);

        return util.convertToResponse(user, serviceUtil);
    }

    public UserResponseForUsers searchByUsername(String username) {
        return util.convertToResponse(findByUsername(username), serviceUtil);
    }

    /**
     * @param service is a TypeService(enum)
     * @return response of all founded users by service
     */

    @Override
    public List<UserResponseForUsers> findUsersBySkill(String service) {
        List<String> allTypesOfServices = serviceUtil.getTypesValue();
        if (!allTypesOfServices.contains(service)) {
            throw new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, service));
        }

        List<User> users = filterUsersByService(repository.findAll(), service);
        return users.stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .collect(Collectors.toList());
    }

    /**
     * @param service    is a TypeService(enum)
     * @param experience is years of work
     * @return response of all founded users by service and years
     */

    @Override
    public List<UserResponseForUsers> findUsersBySkillAndExperience(String service, Integer experience) {
        List<User> users = util.filterUserByExperience(repository.findAll(), experience);
        List<User> filteredUsers = filterUsersByService(users, service);
        return filteredUsers.stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    /**
     * @param service    is a TypeService(enum)
     * @param reputation is the Reputation(enum)
     * @return response of all users which have so skills and reputation
     */

    @Override
    public List<UserResponseForUsers> findUsersBySkillAndReputation(String service, Reputation reputation) {
        List<User> filteredByReputationUsers = repository.findAllByReputation(reputation);
        return filterUsersByService(filteredByReputationUsers, service).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    /**
     * @param firmaTitle is name of Firma
     * @return response of all users which have firma name
     */

    @Override
    public List<UserResponseForUsers> findUsersByFirmaTitle(String firmaTitle) {
        return repository.findAllByFirmaTitle(firmaTitle).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    /**
     * @param serviceTitle is title of service which user have
     * @param experience   is years of work
     * @return response of all users which have such serviceTitle and experience
     */

    @Override
    public List<UserResponseForUsers> findUsersByServiceTitleAndExperience(String serviceTitle, Integer experience) {
        List<User> userByExperience = util.filterUserByExperience(repository.findAll(), experience);
        return util.findAllUsersWhichServiceTitleContainsCriteria(userByExperience, serviceTitle).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    /**
     * @param serviceTitle is experience
     * @param reputation   reputation(enum)
     * @return response of all users which have such serviceTitle and reputation
     */

    @Override
    public List<UserResponseForUsers> findUsersByServiceTitleAndReputation(String serviceTitle, Reputation reputation) {
        List<User> filtererByReputation = repository.findAllByReputation(reputation);
        return util.findAllUsersWhichServiceTitleContainsCriteria(filtererByReputation, serviceTitle).stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public List<UserResponseForUsers> findAllUsers() {
        return repository.findAll().stream()
                .map(user -> util.convertToResponse(user, serviceUtil))
                .toList();
    }

    @Override
    public UserResponseForUsers updateUser(UpdateUserRequest request) {
        User user = findByUsername(request.getUsername());

        User updatedUser = util.updateUser(user, request);
        if (util.checkCriteria(request.getNewServiceTitle())) {
            util.checkOldServiceName(request.getOldServiceTitle(), user.getServices());
            Service service = serviceInterface.findByTitle(user.getServices(), request.getOldServiceTitle());

            if (updatedUser.getServices().contains(service)) {
                Service updatedService = serviceUtil.updateService(service, request.getNewServiceTitle(), request.getMaxPriceOfService(),
                        request.getMinPriceOfService(), request.getTypeOfService());
                serviceInterface.addServiceToUser(updatedService);
            }
        }

        if (util.checkUpdateRequestForSearchNeed(request)) {
            searchNeedService.updateNeeds(request, user);
        }

        repository.save(updatedUser);
        return util.convertToResponse(updatedUser, serviceUtil);
    }


    @Override
    public void deleteUserByUserName(String username) {
        User user = findByUsername(username);
        repository.delete(user);
    }

    public ServiceResponse addNewServiceToUser(AddServiceRequest request) {
        User user = findByUsername(request.getUsername());
        return serviceInterface.addService(request, user);
    }

    public List<ServiceResponse> getAllServicesBelongsUser(String username) {
        User user = findByUsername(username);
        return serviceInterface.getAllServicesBelongsUser(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_USER_MESSAGE, username)));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    private List<User> filterUsersByService(List<User> users, String service) {

        return users.stream()
                .filter(user -> !user.getInformationAboutUser().equalsIgnoreCase("admin"))
                .filter(user -> user.getServices().stream()
                        .anyMatch(ser -> ser.getTypeOfService().toString().equals(service)))
                .toList();
    }

}
