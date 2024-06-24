package com.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
public class ScheduledApplication implements CommandLineRunner {
    private final SomeWork someWork;

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }

    @Override
    public void run(String... args) {
        someWork.get();
    }
}
