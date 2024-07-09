package com.constructor_binding;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "test.constructor")
public class ConstructorBindingConfigProperties {
    private String hello;
    private int number;

    @ConstructorBinding
    public ConstructorBindingConfigProperties(String hello, int number) {
        this.hello = hello;
        this.number = number;
    }

    public ConstructorBindingConfigProperties(int number) {
        this.number = number;
    }
//no setters here
}

