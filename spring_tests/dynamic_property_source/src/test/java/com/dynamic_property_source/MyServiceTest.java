package com.dynamic_property_source;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MyServiceTest {

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:h2:mem:testdb");
        registry.add("spring.datasource.username", () -> "sa");
        registry.add("spring.datasource.password", () -> "password");
    }

    @Autowired
    private Environment environment;

    @Test
    void testDatabaseProperties() {
        assertEquals("jdbc:h2:mem:testdb", environment.getProperty("spring.datasource.url"));
        assertEquals("sa", environment.getProperty("spring.datasource.username"));
        assertEquals("password", environment.getProperty("spring.datasource.password"));
    }
}

