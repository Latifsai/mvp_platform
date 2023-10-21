package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.service.imp.UserServiceImp;
import com.example.platform_mvp.service.utilites.SearchUtil;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import com.example.platform_mvp.service.utilites.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchSuitableUsersService {

    private final SearchUtil util;

    private final UserServiceImp userService;

    private final UserUtil userUtil;

    private final ServiceUtil serviceUtil;


    public List<UserResponseForUsers> findSuitableUsers(String username) {
        List<User> allUsers = userService.getAllUsers();
        List<User> suitableUsers = new ArrayList<>();

        User user = userService.findByUsername(username);
        log.info("user which will get info: " + user.getUsername().toUpperCase());
        if (user.getSearchNeed() != null && util.checkSearchNeed(user.getSearchNeed())) {

            SearchNeed need = user.getSearchNeed();
            log.info("needs: " + need);
            List<TypeOfService> typesOfEveryUser = util.getValues(need.getSearchLabels());
            log.info("Type of user" + typesOfEveryUser.toString().toUpperCase());

            if (need.getSearchLabels() != null && !need.getSearchLabels().trim().isEmpty()) {
                suitableUsers = util.getSuitableUsers(typesOfEveryUser, allUsers, suitableUsers, user);
                log.info("labels step: " + suitableUsers.toString().toUpperCase());
            }

            if (need.getPrice() != null && !need.getPrice().equals(BigDecimal.ZERO)) {
                suitableUsers = util.filterByPrises(suitableUsers, need);
                log.info("price step: " + suitableUsers.toString().toUpperCase());
            }

            if (need.getExperience() != null && need.getExperience() != 0) {
                suitableUsers = util.filterByExperience(suitableUsers, need);
                log.info("experience step: " + suitableUsers.toString().toUpperCase());
            }

            if (need.getReputation() != null) {
                suitableUsers = util.filterByReputation(suitableUsers, need.getReputation());
                log.info("reputation step: " + suitableUsers.toString().toUpperCase());
            }
        }

        log.info(suitableUsers.toString());
        return suitableUsers.stream()
                .map(u -> userUtil.convertToResponse(u, serviceUtil))
                .toList();
    }
}
