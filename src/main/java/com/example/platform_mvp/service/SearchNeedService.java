package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.entities.enums.Reputation;

import java.math.BigDecimal;

public interface SearchNeedService {
    SearchNeed addNeedsToUser(String labels, BigDecimal price, Integer experience, Reputation reputation, User user);

    SearchNeed updateNeeds(UpdateUserRequest request, User user);

}
