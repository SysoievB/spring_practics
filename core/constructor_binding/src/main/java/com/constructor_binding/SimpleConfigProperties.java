package com.constructor_binding;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "test.simple")
public class SimpleConfigProperties {
    private String hello;
    private int number;
    //setters used for binding
}
