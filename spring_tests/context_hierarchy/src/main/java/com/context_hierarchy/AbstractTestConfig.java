package com.context_hierarchy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbstractTestConfig {

    @Bean
    public AbstractGreeting abstractGreeting() {
        return new AbstractGreeting();
    }
}
