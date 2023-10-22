package com.example.platform_mvp.service.utilites.generator;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class PasswordGenerator {

    private static final String MATERIAL = "qwertyuiopasdfghjklzxcvbnm1234567890";

    public static String generateNumber() {
        Random random = new SecureRandom();

        StringBuilder stringBuilder = new StringBuilder(random.nextInt(8, 21));
        for (int i = 0; i < stringBuilder.capacity(); i++) {
            stringBuilder.append(MATERIAL.charAt(random.nextInt(MATERIAL.length())));
        }
        log.info("created password: " + stringBuilder);
        return stringBuilder.toString();
    }
}
