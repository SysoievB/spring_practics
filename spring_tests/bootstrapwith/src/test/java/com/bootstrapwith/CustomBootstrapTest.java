package com.bootstrapwith;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@BootstrapWith(CustomTestContextBootstrapper.class)
@SpringJUnitConfig // Combines @ExtendWith(SpringExtension.class) and @ContextConfiguration
public class CustomBootstrapTest {

    @Test
    void testCustomBootstrapper() {
        System.out.println("Running test with custom bootstrapper");
    }

    @Configuration
    static class TestConfig {
        @Bean
        public String sampleBean() {
            return "Sample Bean";
        }
    }
}

