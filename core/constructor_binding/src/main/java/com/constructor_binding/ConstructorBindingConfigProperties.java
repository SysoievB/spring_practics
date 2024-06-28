package com.constructor_binding;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "test.constructor")
public class ConstructorBindingConfigProperties {
    private String hello;
    private int number;
    @ConstructorBinding
    public ConstructorBindingConfigProperties() {
    }
}

