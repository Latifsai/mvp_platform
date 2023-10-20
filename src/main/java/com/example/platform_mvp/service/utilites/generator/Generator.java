package com.example.platform_mvp.service.utilites.generator;

import com.example.platform_mvp.entities.enums.TypeOfService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.platform_mvp.entities.enums.TypeOfService.*;
import static com.example.platform_mvp.entities.enums.TypeOfService.Cosmetic;

public class Generator {
    public static Map<String, List<TypeOfService>> getLabelsForUser() {
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

}
