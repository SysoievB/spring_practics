package com.constructor_binding;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@AllArgsConstructor
public class ConstructorBindingApplication implements CommandLineRunner {
    private final SimpleConfigProperties simpleConfigProperties;
    private final ConstructorBindingConfigProperties constructorBindingConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(ConstructorBindingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(simpleConfigProperties.getHello());
        System.out.println(simpleConfigProperties.getNumber());

        System.out.println(constructorBindingConfigProperties.getHello());
        System.out.println(constructorBindingConfigProperties.getNumber());
    }
}
