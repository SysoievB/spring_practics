package com.test_execution_listener;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * <h3>@TestExecutionListeners</h3> is used to register listeners for the annotated test class,
 * its subclasses, and its nested classes. */
@SpringJUnitConfig
@TestExecutionListeners(CustomTestExecutionListener.class)
public class TestExecutionListenerExample {

    @Test
    void exampleTest1() {
        System.out.println("Executing exampleTest1");
    }

    @Test
    void exampleTest2() {
        System.out.println("Executing exampleTest2");
    }
}
