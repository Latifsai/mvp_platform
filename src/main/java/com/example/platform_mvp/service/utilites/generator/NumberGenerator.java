package com.example.platform_mvp.service.utilites.generator;

import java.security.SecureRandom;
import java.util.Random;

public final class NumberGenerator {

    private static NumberGenerator instance;

    private NumberGenerator() {
    }

    public static NumberGenerator getInstance() {

        if (instance == null) {
            instance = new NumberGenerator();
        }
        return instance;
    }

    private static final String MATERIAL = "QWE1RTY2UIO3PAS4DFG5HJK6KLZ7XCV8BNM9";

    public String generateNumber(int length) {
        Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(MATERIAL.charAt(random.nextInt(MATERIAL.length())));
        }
        return stringBuilder.toString();
    }


}
