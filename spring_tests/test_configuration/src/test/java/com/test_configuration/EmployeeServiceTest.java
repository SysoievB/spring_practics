package com.test_configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * <h3>Overriding Beans in the Test Context</h3>
 * When testing components, you might need to replace certain
 * production beans with test-specific alternatives.
 * */
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void testFindUserById() {
        String result = employeeService.findEmployeeById(1);
        assertEquals("TESTEmployee1", result);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService() {
                @Override
                public String findEmployeeById(int id) {
                    return "TESTEmployee" + id;
                }
            };
        }
    }
}