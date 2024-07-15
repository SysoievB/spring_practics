package com.specifications;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class SpecificationsApplication {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpecificationsApplication.class, args);
    }

    @Bean
    public CommandLineRunner startUp() {
        return args -> {
            System.out.println("hello");
        };
    }
}
