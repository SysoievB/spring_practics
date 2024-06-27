package com.property_source;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config.properties")
public class PropertySourceConfig {
    @Value("${car.name}")
    private String name;
    @Value("${car.year}")
    private int year;
}
