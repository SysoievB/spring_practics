package com.context_configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h3>@ContextConfiguration:</h3>
 * <p>
 * Loads only the specified configuration class (TestConfig).
 * <p>
 * Beans not explicitly defined in TestConfig are unavailable.
 * <p>
 * Suitable for lightweight tests of a subset of components.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class GreetingServiceTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    void testGreet() {
        String result = greetingService.greet("Bob");
        assertEquals("Hello, Bob", result);
    }
}
