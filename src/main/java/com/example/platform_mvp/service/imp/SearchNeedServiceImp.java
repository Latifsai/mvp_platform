package com.example.platform_mvp.service.imp;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.User;
import com.example.platform_mvp.repository.SearchNeedRepository;
import com.example.platform_mvp.service.SearchNeedService;
import com.example.platform_mvp.service.utilites.SearchNeedsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchNeedServiceImp implements SearchNeedService {

    private final UserServiceImp userService;

    private final SearchNeedsUtil util;

    private final SearchNeedRepository repository;

    @Override
    public SearchNeedsResponse addNeedsToUser(AddNeedsRequest request) {
        User user = userService.findByUsername(request.getUsername());
        SearchNeed need = util.createEntity(request);

        user.setSearchNeed(need);
        need.setUser(user);

        repository.save(need);
        return util.convertToResponse(need);
    }


    @Override
    public SearchNeedsResponse updateNeeds(UpdateNeedsRequest request) {
        User user = userService.findByUsername(request.getUsername());
        SearchNeed need = user.getSearchNeed();
        SearchNeed updatedNeed = util.updateNeeds(need, request);
        repository.save(updatedNeed);
        return util.convertToResponse(updatedNeed);
    }
}
