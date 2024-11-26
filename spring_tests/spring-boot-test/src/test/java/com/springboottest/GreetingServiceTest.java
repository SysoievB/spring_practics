package com.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h3>@SpringBootTest:</h3>
 * <p>
 * Loads the full Spring Boot application context.
 * <p>
 * All beans and configurations from the application are included.
 * <p>
 * Suitable for testing the integration of multiple components.
 * */
@SpringBootTest(classes = SpringBootTestApplication.class)
class GreetingServiceTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    void testGreet() {
        String result = greetingService.greet("Alice");
        assertEquals("Hello, Alice", result);
    }
}
