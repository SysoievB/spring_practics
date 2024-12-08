package com.context_hierarchy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h3>What are advantages of using @ContextHierarchy over pure @ContextConfiguration</h3>
 * Also it works with -> <h6>@ContextConfiguration(classes = {TestConfig.class, AbstractTestConfig.class})</h6>
 * Beans in child context see beans in parent context, but not otherwise, so you can isolate different
 * parts of your item under test.
 * */
@ExtendWith(SpringExtension.class)
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = AbstractTestConfig.class),
        @ContextConfiguration(name = "child", classes = TestConfig.class)
})
public class GreetingServiceTest {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private AbstractGreeting abstractGreeting;

    @Test
    void testAbstractGreeting() {
        String result = abstractGreeting.abstractGreeting("Bob");
        assertEquals("Hello, from abstract Bob", result);
    }

    @Test
    void testGreet() {
        String result = greetingService.greet("Bob");
        assertEquals("Hello, Bob", result);
    }
}