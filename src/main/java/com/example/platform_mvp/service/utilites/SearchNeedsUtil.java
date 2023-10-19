package com.example.platform_mvp.service.utilites;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.entities.SearchNeed;
import com.example.platform_mvp.entities.enums.TypeOfService;
import com.example.platform_mvp.validation.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.platform_mvp.entities.enums.TypeOfService.*;

@Service
public class SearchNeedsUtil {

    public SearchNeed createEntity(AddNeedsRequest request) {
        SearchNeed searchNeed = new SearchNeed();

        for (String label: getAllLabels()) {
            if (label.equalsIgnoreCase(request.getSearchLabels())) {
                searchNeed.setSearchLabels(request.getSearchLabels());
            }else {
                throw new IllegalArgumentException(String.format(ExceptionMessage.INCORRECT_LABEL_MESSAGE,
                        request.getSearchLabels(), getLabelsForUser().toString()));
            }
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

    public Map<String, List<TypeOfService>> getLabelsForUser() {
        Map<String, List<TypeOfService>> map = new HashMap<>();
        map.put("#software", List.of(IT));
        map.put("#it", List.of(IT));
        map.put("#bank", List.of(Accounting, Financial, Accounting));
        map.put("#money", List.of(Accounting, Financial, Accounting));
        map.put("#rules", List.of(Legal));
        map.put("#attorney", List.of(Legal));
        map.put("#house", List.of(Housing_and_communal, Construction));
        map.put("#finance", List.of(Accounting, Financial, Accounting));
        map.put("#kurs",  List.of(Educational));
        map.put("#education", List.of(Educational));
        map.put("#delivery", List.of(Delivery));
        map.put("#logistic", List.of(Delivery));
        map.put("#protect", List.of(Security));
        map.put("#savety", List.of(Security));
        map.put("#security", List.of(Security));
        map.put("#fly",  List.of(Tourist));
        map.put("#holiday", List.of(Tourist));
        map.put("#regeneration", List.of(Entertainment_and_recreation, Medical_and_sanatorium));
        map.put("#rest", List.of(Entertainment_and_recreation, Medical_and_sanatorium));
        map.put("#healt", List.of(Entertainment_and_recreation, Medical_and_sanatorium));
        map.put("#medizine", List.of(Entertainment_and_recreation, Medical_and_sanatorium));
        map.put("#beauty",  List.of(Cosmetic));
        map.put("#nails", List.of(Cosmetic));
        return map;
    }

    public Set<String> getAllLabels() {
        return getLabelsForUser().keySet();
    }
}
