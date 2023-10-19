package com.example.platform_mvp.service;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;

public interface SearchNeedService {
    SearchNeedsResponse addNeedsToUser(AddNeedsRequest request);
    SearchNeedsResponse updateNeeds(UpdateNeedsRequest request);

}
