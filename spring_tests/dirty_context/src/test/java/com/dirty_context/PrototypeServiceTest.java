package com.dirty_context;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PrototypeServiceTest {

    @Autowired
    ApplicationContext context;

    @Test
    @Order(1)
    void testPrototypeScopeWithoutDirtiesContext() {
        // Retrieve two instances of PrototypeService
        PrototypeService service1 = context.getBean(PrototypeService.class);
        PrototypeService service2 = context.getBean(PrototypeService.class);

        // Each instance should be separate due to prototype scope
        service1.incrementCounter();
        assertEquals(1, service1.getCounter());
        assertEquals(0, service2.getCounter());
    }

    @Order(2)
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testPrototypeScopeWithDirtiesContext() {
        // Retrieve a new instance after context reset
        PrototypeService service = context.getBean(PrototypeService.class);
        assertEquals(0, service.getCounter()); // Counter reset to 0
        service.incrementCounter();
        assertEquals(1, service.getCounter());
    }

    @Order(3)
    @Test
    void testAfterDirtiesContext() {
        // Context should be reset, so a fresh PrototypeService instance is obtained
        PrototypeService service = context.getBean(PrototypeService.class);
        assertEquals(0, service.getCounter()); // Counter reset due to @DirtiesContext
    }

    @Order(4)
    @Test
    void testAfterDirtiesContextIncremented() {
        PrototypeService service = context.getBean(PrototypeService.class);
        service.incrementCounter();
        assertEquals(1, service.getCounter());
        service.incrementCounter();
        assertEquals(2, service.getCounter());
        service.incrementCounter();
        assertEquals(3, service.getCounter());
    }

    @Order(5)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testAfterDirtiesContextIncrementedCheck() {
        PrototypeService service = context.getBean(PrototypeService.class);
        assertEquals(0, service.getCounter());
    }
}