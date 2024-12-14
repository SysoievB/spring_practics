package com.record_application_events;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecordApplicationEventsApplication implements CommandLineRunner {
    private UserService userService;

    public RecordApplicationEventsApplication(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Simulate normal publishing
            userService.publishContent("Alice", "Hello World!");

            // Simulate exception-triggering publishing
            userService.publishContent("Bob", "This contains error");
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RecordApplicationEventsApplication.class, args);
    }

}
