package com.test_property_source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "custom.property=testValue",
        "another.property=123"
})
//@TestPropertySource(locations = "classpath:test-application.properties")//can be added separate file with properties
public class MyServiceTest {

    @Autowired
    private Environment environment;

    @Test
    void testInlineProperties() {
        String customProperty = environment.getProperty("custom.property");
        assertEquals("testValue", customProperty);

        String anotherProperty = environment.getProperty("another.property");
        assertEquals("123", anotherProperty);
    }
}

