package com.test_property_source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "static.property=staticValue"
})
public class MyDynamicServiceTest {

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("dynamic.property", () -> "dynamicValue");
    }

    @Autowired
    private Environment environment;

    @Test
    void testDynamicAndStaticProperties() {
        assertEquals("staticValue", environment.getProperty("static.property"));
        assertEquals("dynamicValue", environment.getProperty("dynamic.property"));
    }
}

