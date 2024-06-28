package com.conditional_on_property;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class ConditionalOnPropertyApplication implements CommandLineRunner {
    private final MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(ConditionalOnPropertyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myService.performAction();
    }
}
