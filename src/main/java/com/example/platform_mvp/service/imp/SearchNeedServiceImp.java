package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.repository.SearchNeedRepository;
import com.example.platform_mvp.service.SearchNeedService;
import com.example.platform_mvp.service.utilites.SearchNeedsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SearchNeedServiceImp implements SearchNeedService {

    private final SearchNeedsUtil util;

    private final SearchNeedRepository repository;

    @Override
    public SearchNeed addNeedsToUser(String labels, BigDecimal price, Integer experience, Reputation reputation, User user) {
        SearchNeed need = util.createEntity(labels, price, experience, reputation);

        user.setSearchNeed(need);
        need.setUser(user);

        return repository.save(need);
    }


    @Override
    public SearchNeed updateNeeds(UpdateUserRequest request, User user) {
        SearchNeed need = user.getSearchNeed();
        SearchNeed updatedNeed = util.updateNeeds(need, request);
        return repository.save(updatedNeed);
    }
}
