package com.configuration_properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "test.logging")
public class ConfigLoggingProperties {
    private String hello;
    private int number;
}
