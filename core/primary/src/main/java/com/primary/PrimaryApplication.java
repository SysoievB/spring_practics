package com.primary;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class PrimaryApplication implements CommandLineRunner {
    private final Animal animal;

    public static void main(String[] args) {
        SpringApplication.run(PrimaryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        animal.eat();
    }
}
