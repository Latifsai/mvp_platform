package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.service.utilites.generator.Generator;
import com.example.platform_mvp.validation.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class SearchNeedsUtil {

    public SearchNeed createEntity(AddNeedsRequest request) {
        SearchNeed searchNeed = new SearchNeed();

        boolean match = getAllLabels().stream()
                .anyMatch(label -> label.equalsIgnoreCase(request.getSearchLabels()));

            if (match) {
                searchNeed.setSearchLabels(request.getSearchLabels());
            }else {
                throw new IllegalArgumentException(String.format(ExceptionMessage.INCORRECT_LABEL_MESSAGE,
                        request.getSearchLabels(), getAllLabels().toString()));
            }

        searchNeed.setPrice(request.getPrice());
        searchNeed.setExperience(request.getExperience());
        searchNeed.setReputation(request.getReputation());
        return searchNeed;
    }

    public SearchNeedsResponse convertToResponse(SearchNeed need) {
        return SearchNeedsResponse.builder()
                .searchLabels(need.getSearchLabels())
                .price(need.getPrice())
                .reputation(need.getReputation())
                .experience(need.getExperience())
                .build();
    }

    public SearchNeed updateNeeds(SearchNeed need, UpdateNeedsRequest request) {
        if (request.getSearchLabels() != null && !request.getSearchLabels().trim().isEmpty()) need.setSearchLabels(request.getSearchLabels());
        if (request.getPrice() != null && !request.getPrice().equals(BigDecimal.ONE)) need.setPrice(request.getPrice());
        if (request.getReputation() != null) need.setReputation(request.getReputation());
        if (request.getExperience() !=null && request.getExperience() != 0) need.setExperience(request.getExperience());
        return need;
    }


    public Set<String> getAllLabels() {
        return Generator.getLabelsForUser().keySet();
    }
}
