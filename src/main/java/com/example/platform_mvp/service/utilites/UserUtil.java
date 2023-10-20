package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.user.RegistrationUserRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.AlreadyExistException;
import com.example.platform_mvp.validation.exceptions.FormatException;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserUtil {

    private final String usernameFormat = "^[A-Za-z]{3,20}[0-9]{2,5}$";
    private final String passwordFormat = "^[0-9]{6,20}[A-Za-z]{3,6}$";

    private Matcher matcher;


    public User createUserFromRequest(RegistrationUserRequest request, List<User> allUsers) {
        User user = new User();

        List<String> usernames = allUsers.stream()
                .map(User::getUsername)
                .toList();

        if (!usernames.contains(request.getUsername())) {
            checkField(usernameFormat, request.getUsername());

            if (matcher.find()) {
                user.setUsername(request.getUsername());
            } else {
                throw new FormatException(String.format(ExceptionMessage.INCORRECT_USERNAME_FORMAT_MESSAGE, request.getUsername()));
            }
        } else {
            throw new AlreadyExistException(String.format(ExceptionMessage.ALREADY_EXIST_MESSAGE, request.getUsername()));
        }

        user.setFirstName(request.getFirstName());
        user.setSurname(request.getSurname());

        checkField(passwordFormat, request.getPassword());

        if (matcher.find()) {
            user.setPassword(request.getPassword());
        } else {
            throw new FormatException(String.format(ExceptionMessage.INCORRECT_PASSWORD_FORMAT_MESSAGE, request.getPassword()));
        }
        user.setExperience(request.getExperience());
        user.setInformationAboutUser(request.getUserInfo());
        user.setFirmaTitle(request.getFirmaTitle());
        setCreditsAndReputation(user, request);
        return user;
    }

    private void setCreditsAndReputation(User user, RegistrationUserRequest request) {
        int years = request.getExperience();
        if (years >= 10) {
            user.setCredits(100);
            user.setReputation(Reputation.DIAMOND);
        } else if (years >= 6) {
            user.setCredits(65);
            user.setReputation(Reputation.SENIOR);
        } else if (years >= 3) {
            user.setCredits(46);
            user.setReputation(Reputation.GOOD_SPECIALIST);
        } else if (years >= 1) {
            user.setCredits(23);
            user.setReputation(Reputation.PRACTITIONER_BEGINNER);
        }
    }

    private void checkField(String regex, String field) {
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(field);
    }

    public UserResponseForUsers convertToResponse(User user, ServiceUtil serviceUtil) {
        List<ServiceResponse> services = user.getServices().stream()
                .map(serviceUtil::convertToResponse)
                .toList();

        return UserResponseForUsers.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .firmaTitle(user.getFirmaTitle())
                .experience(user.getExperience())
                .informationAboutUser(user.getInformationAboutUser())
                .reputation(user.getReputation())
                .services(services)
                .build();
    }

    public List<User> filterUserByExperience(List<User> allUsers, int years) {
        List<User> users = new ArrayList<>();
        int changer = 3;

        for (User user : allUsers) {
            if (user.getExperience() >= years || user.getExperience() >= years + changer
                    || user.getExperience() >= years - changer) {
                users.add(user);
            }
        }
        return users;
    }

    public User updateUser(User user, UpdateUserRequest request) {
        if (checkCriteria(request.getUsername())) user.setUsername(request.getUsername());
        if (checkCriteria(request.getFirstName())) user.setFirstName(request.getFirstName());
        if (checkCriteria(request.getSurname())) user.setSurname(request.getSurname());

        checkField(passwordFormat, request.getPassword());
        if (checkCriteria(request.getPassword()) && matcher.find()) user.setPassword(request.getPassword());
        if (checkCriteria(request.getUserInfo())) user.setInformationAboutUser(request.getUserInfo());



        if (request.getExperience() > 0 && request.getExperience() != null) user.setExperience(request.getExperience());
        return user;
    }

    public List<User> findAllUsersWhichServiceTitleContainsCriteria(List<User> users, String serviceTitle) {
        String firstWord = checkStringTitle(serviceTitle);
        return users.stream()
                .filter(user -> user.getServices().stream()
                        .allMatch(service -> service.getServiceTitle().toLowerCase().contains(firstWord.toLowerCase())))
                .toList();
    }

    public boolean checkCriteria(String criteria) {
        return criteria != null && !criteria.trim().isEmpty();
    }

    private String checkStringTitle(String title) {
        return title.contains(" ")
                ? title.substring(0, title.indexOf(" "))
                : title;
    }

    public void checkOldServiceName(String oldName, List<com.example.platform_mvp.entities.Service> services) {
        services.stream()
                .map(service -> service.getServiceTitle().equalsIgnoreCase(oldName))
                .findAny()
                .orElseThrow(() ->  new NotFoundException(String.format(ExceptionMessage.NOT_FOUND_SERVICE_MESSAGE, oldName)));
    }
}
