package com.import_config;

import com.import_config.model.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigA {

    @Bean
    A a() {
        return new A("A class");
    }
}
