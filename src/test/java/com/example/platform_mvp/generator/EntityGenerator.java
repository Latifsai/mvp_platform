//package com.example.platform_mvp.generator;
//
//import com.example.platform_mvp.entities.Service;
//import com.example.platform_mvp.entities.User;
//import com.example.platform_mvp.entities.enums.Reputation;
//import com.example.platform_mvp.entities.enums.TypeOfService;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.UUID;
//
//public class EntityGenerator {
//
//    public static Service getService() {
//        return new Service(
//                1L,
//                "Software Developing",
//                BigDecimal.valueOf(50000.00),
//                BigDecimal.valueOf(4500.00),
//                TypeOfService.IT
//        );
//    }
//
//    public static User getUser() {
//        return new User(
//                UUID.fromString("id"),
//                "username231",
//                "Oleg",
//                "50151832Lat",
//                "Smidth",
//                "It solutions",
//                5,
//                "i am owner of firma 'It`s' we are developing the best SW for you",
//                50,
//                Reputation.GOOD_SPECIALIST,
//                Collections.singletonList(getService())
//        );
//    }
//
//}
