package com.example.platform_mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlatformMvpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformMvpApplication.class, args);
    }

}
