package com.sparta.team6project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Team6projectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team6projectApplication.class, args);
    }

}
