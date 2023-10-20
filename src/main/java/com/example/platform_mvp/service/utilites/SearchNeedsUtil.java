package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.dto.user.UpdateUserRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.enums.Reputation;
import com.example.platform_mvp.service.utilites.generator.GeneratorMap;
import com.example.platform_mvp.validation.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class SearchNeedsUtil {

    public SearchNeed createEntity(String labels, BigDecimal price, Integer experience, Reputation reputation) {
        SearchNeed searchNeed = new SearchNeed();

        boolean match = getAllLabels().stream()
                .anyMatch(label -> label.equalsIgnoreCase(labels));

        if (labels != null && !labels.trim().isEmpty()) {
            if (match) {
                searchNeed.setSearchLabels(labels);
            } else {
                throw new IllegalArgumentException(String.format(ExceptionMessage.INCORRECT_LABEL_MESSAGE, labels, getAllLabels().toString()));
            }
        }
        searchNeed.setPrice(price);
        searchNeed.setExperience(experience);
        searchNeed.setReputation(reputation);
        return searchNeed;
    }

    @Deprecated
    public SearchNeedsResponse convertToResponse(SearchNeed need) {
        return SearchNeedsResponse.builder()
                .searchLabels(need.getSearchLabels())
                .price(need.getPrice())
                .reputation(need.getReputation())
                .experience(need.getExperience())
                .build();
    }

    public SearchNeed updateNeeds(SearchNeed need, UpdateUserRequest request) {
        if (request.getLabels() != null && !request.getLabels().trim().isEmpty()) need.setSearchLabels(request.getLabels());

        if (request.getWantedPrice() != null && !request.getWantedPrice().equals(BigDecimal.ONE)) need.setPrice(request.getWantedPrice());

        if (request.getWandetReputation() != null) need.setReputation(request.getWandetReputation());

        if (request.getWantedExperience() != null && request.getWantedExperience() != 0) need.setExperience(request.getExperience());
        return need;
    }

    public Set<String> getAllLabels() {
        return GeneratorMap.getLabelsForUser().keySet();
    }

}
