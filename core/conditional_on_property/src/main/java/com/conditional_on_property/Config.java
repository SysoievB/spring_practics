package com.conditional_on_property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
/**
 *   <h3>matchIfMissing = true</h3>
 *   If we commented my.service.enabled condition will match anyway.
 *   If we will remove value from properties -> my.service.enabled=
 *   -> application won`t even start.
 *   */
    @Bean
    @ConditionalOnProperty(name = "my.service.enabled", havingValue = "true", matchIfMissing = true)
    MyService myService() {
        return new MyServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(name = "my.service.enabled", havingValue = "false")
    public MyService myAlternativeService() {
        return new MyAlternativeServiceImpl();
    }
}
