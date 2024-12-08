package com.context_hierarchy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig extends AbstractTestConfig {

    @Bean
    public GreetingService greetingService() {
        return new GreetingService();
    }
}

