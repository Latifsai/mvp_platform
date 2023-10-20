package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.service.imp.UserServiceImp;
import com.example.platform_mvp.service.utilites.SearchUtil;
import com.example.platform_mvp.service.utilites.ServiceUtil;
import com.example.platform_mvp.service.utilites.UserUtil;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        findSuitableUsers();
    }

    public List<UserResponseForUsers> findSuitableUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<User> suitableUsers = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getSearchNeed() != null && util.checkSearchNeed(user.getSearchNeed())) {
                List<SearchNeed> needs = new ArrayList<>();
                needs.add(user.getSearchNeed());

                for (SearchNeed need : needs) {
                    List<TypeOfService> typesOfEveryUser = util.getValues(need.getSearchLabels());
                    log.info("Type of user" + typesOfEveryUser.toString());

                    if (need.getSearchLabels() != null && !need.getSearchLabels().trim().isEmpty()) {
                        suitableUsers = util.getSuitableUsers(typesOfEveryUser, allUsers, suitableUsers, user);
                        log.info("labels step: " + suitableUsers.toString());
                    }

                    if (need.getPrice() != null && !need.getPrice().equals(BigDecimal.ZERO)) {
                        suitableUsers = util.filterByPrises(suitableUsers, need);
                        log.info("price step: " + suitableUsers.toString());
                    }

                    if (need.getExperience() != null && need.getExperience() != 0) {
                        suitableUsers = util.filterByExperience(suitableUsers, need);
                        log.info("experience step: " + suitableUsers.toString());
                    }

                    if (need.getReputation() != null) {
                        suitableUsers = util.filterByReputation(suitableUsers, need.getReputation());
                        log.info("reputation step: " + suitableUsers.toString());
                    }
                }
            }
        }
        log.info(suitableUsers.toString());
        return suitableUsers.stream()
                .map(user -> userUtil.convertToResponse(user, serviceUtil))
                .toList();
    }
}
