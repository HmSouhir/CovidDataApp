package com.example.coviddataapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidDataAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidDataAppApplication.class, args);
    }

}
