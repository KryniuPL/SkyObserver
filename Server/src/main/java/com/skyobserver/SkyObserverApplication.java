package com.skyobserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SkyObserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyObserverApplication.class, args);
    }
}
