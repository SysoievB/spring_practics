package com.component_config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CarProvider {
    Car getCar() {
        return new Car("BMW", "black", LocalDate.now());
    }
}
