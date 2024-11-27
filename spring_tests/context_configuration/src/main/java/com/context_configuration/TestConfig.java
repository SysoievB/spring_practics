package com.context_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public GreetingService greetingService() {
        return new GreetingService();
    }
}

