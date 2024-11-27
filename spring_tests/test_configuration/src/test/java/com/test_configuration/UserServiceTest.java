package com.test_configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * <h3>Overriding Beans in the Test Context</h3>
 * When testing components, you might need to replace certain production beans with
 * test-specific alternatives.
 * */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testFindUserById() {
        String result = userService.findUserById(1);
        assertEquals("TestUser1", result);
    }

    /**
     * The TestConfig class overrides the UserService bean with a test-specific version.
     * This allows you to simulate different behaviors for the UserService during tests.
     * */
    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserService userService() {
            return new UserService() {
                @Override
                public String findUserById(int id) {
                    return "TestUser" + id;
                }
            };
        }
    }
}
