package com.import_config;

import com.import_config.model.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(ConfigA.class)
@Configuration
public class ConfigB {

    @Bean
    B b() {
        return new B("B class");
    }
}
