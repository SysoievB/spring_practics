package com.test_configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <h3>Adding Test-Only Beans</h3>
 * You might need additional helper beans only during testing.
 * */
@SpringBootTest
public class SomeTest {

    @Autowired
    private String testHelper;

    @Test
    void testHelperBean() {
        System.out.println(testHelper); // Test Helper Bean
    }

    /**
     * The TestConfig class provides a testHelper bean that exists only in the test environment.
     * This bean will not be included in the production context.
     * */
    @TestConfiguration
    static class TestConfig {
        @Bean
        public String testHelper() {
            return "Test Helper Bean";
        }
    }
}
