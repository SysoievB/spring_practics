package com.application_events;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationEventsApplication implements CommandLineRunner {
	private UserService userService;

	public ApplicationEventsApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationEventsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		userService.publishContent("Alice", "Hello World!");
		userService.publishContent("Bob", "Spring Events are cool!");
	}
}
