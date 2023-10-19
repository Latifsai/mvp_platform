package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.service.ServiceResponse;
import com.example.platform_mvp.dto.user.RegistrationAndUpdateUserRequest;
import com.example.platform_mvp.dto.user.UserRequestForUsers;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.AlreadyExistException;
import com.example.platform_mvp.validation.exceptions.FormatException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserUtil {

    private final String usernameFormat = "^[A-Za-z]{5,20}[0-9]{2,5}$";
    private final String passwordFormat = "^[0-9]{6,20}[A-Za-z]{3,6}$";

    private Matcher matcher;


    public User createUserFromRequest(RegistrationAndUpdateUserRequest request, List<User> allUsers) {
        User user = new User();

        List<String> usernames = allUsers.stream()
                .map(User::getUsername)
                .toList();

        if (usernames.contains(request.getUsername())) {
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

    private void setCreditsAndReputation(User user, RegistrationAndUpdateUserRequest request) {
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

    public UserRequestForUsers convertToResponse(User user, ServiceUtil serviceUtil) {
        List<ServiceResponse> services = user.getServices().stream()
                .map(serviceUtil::convertToResponse)
                .toList();

        return UserRequestForUsers.builder()
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

    public User updateUser(User user, RegistrationAndUpdateUserRequest request) {
        if (checkCriteria(request.getUsername())) user.setUsername(request.getUsername());
        if (checkCriteria(request.getFirstName())) user.setUsername(request.getFirstName());
        if (checkCriteria(request.getSurname())) user.setUsername(request.getSurname());

        checkField(passwordFormat, request.getPassword());
        if (checkCriteria(request.getPassword()) && matcher.find()) user.setUsername(request.getPassword());
        if (checkCriteria(request.getUserInfo())) user.setInformationAboutUser(request.getUserInfo());

        if (request.getExperience() > 0 && request.getExperience() != null) user.setExperience(request.getExperience());
        return user;
    }

    public boolean checkCriteria(String criteria) {
        return !criteria.isBlank();
    }
}
