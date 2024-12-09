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

    /**
     * <h3>@DynamicPropertySource</h3> is an annotation that can be applied to methods in integration
     * test classes that need to register dynamic properties to be added to the set of
     * PropertySources in the Environment for an ApplicationContext loaded for an integration
     * test. Dynamic properties are useful when you do not know the value of the properties
     * upfront – for example, if the properties are managed by an external resource such as
     * for a container managed by the Testcontainers project.
     * */
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

