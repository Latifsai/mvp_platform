package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.Service;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.service.utilites.generator.GeneratorOfLabels;
import com.example.platform_mvp.validation.ExceptionMessage;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchUtil {

    public boolean checkSearchNeed(SearchNeed need) {
        return (need.getSearchLabels() != null && !need.getSearchLabels().trim().isEmpty()) ||
                (need.getExperience() != null && need.getExperience() != 0) ||
                (need.getPrice() != null && !need.getPrice().equals(BigDecimal.ZERO))  ||
                (need.getReputation() != null);
    }

    public List<User> filterByReputation(List<User> suitableUsers, Reputation reputation) {
        return suitableUsers.stream()
                .filter(user -> user.getReputation().equals(reputation))
                .toList();
    }

    public List<User> filterByExperience(List<User> suitableUsers, SearchNeed need) {
        return suitableUsers.stream()
                .filter(user -> checkByExperience(user, need.getExperience()))
                .toList();
    }

    public List<User> filterByPrises(List<User> suitableUsers, SearchNeed need) {
        return suitableUsers.stream()
                .filter(us -> us.getServices().stream()
                        .allMatch(service -> checkByPrice(service, need.getPrice())))
                .toList();
    }

    private boolean checkByExperience(User user, Integer experience) {
        Integer changer = experience / 2;
        Integer userEx = user.getExperience();
        int minus = experience - changer;
        int plus = experience + changer;

        return experience <= userEx || minus <= userEx || plus <= userEx;
    }

    private boolean checkByPrice(Service service, BigDecimal price) {
        BigDecimal max = service.getMinPrice();
        BigDecimal min = service.getMaxPrice();
        BigDecimal changer = getChanger(price);

        BigDecimal priceMinusChanger = price.subtract(changer);
        BigDecimal pricePlusChanger = price.add(changer);

        return (price.compareTo(max) >= 0 || price.compareTo(min) >= 0)
                || (priceMinusChanger.compareTo(max) >= 0 || priceMinusChanger.compareTo(min) >= 0)
                || (pricePlusChanger.compareTo(max) >= 0 || pricePlusChanger.compareTo(min) >= 0);
    }

    private BigDecimal getChanger(BigDecimal price) {
        BigDecimal percent = new BigDecimal("0.5");
        return price.multiply(percent);
    }

    public List<User> getSuitableUsers(List<TypeOfService> typesOfEveryUser, List<User> allUsers, List<User> suitableUsers, User user) {
        List<User> mutableSuitableUsers = new ArrayList<>(suitableUsers); // Создайте изменяемую копию suitableUsers

        for (TypeOfService type : typesOfEveryUser) {
            Optional<User> userToSave = allUsers.stream()
                    .filter(us -> us.getServices().stream()
                            .allMatch(service -> service.getTypeOfService().equals(type)))
                    .findAny();

            userToSave.ifPresent(savedUser -> {
                if (!user.getUsername().equals(savedUser.getUsername())) {
                    mutableSuitableUsers.add(savedUser);
                }
            });
        }
        return mutableSuitableUsers;

    }

    public List<TypeOfService> getValues(String labels) {
        assert labels != null;
        Map<String, List<TypeOfService>> map = GeneratorOfLabels.getLabelsForUser();
        log.info(map.toString());

        boolean containLabel = map.keySet().stream()
                .filter(Objects::nonNull)
                .anyMatch(str -> str.contains(labels));
        log.info("containLabel" + containLabel);
        if (containLabel) {
            return map.get(labels);
        }

        throw new NotFoundException(String.format(ExceptionMessage.INCORRECT_LABEL_MESSAGE, labels));
    }
}
