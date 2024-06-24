package com.bean_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class CarConfig {

    @Bean
    Car car() {
        return new Car("BMW", "black", LocalDate.now());
    }
}
