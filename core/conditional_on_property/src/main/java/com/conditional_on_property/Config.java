package com.conditional_on_property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

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
