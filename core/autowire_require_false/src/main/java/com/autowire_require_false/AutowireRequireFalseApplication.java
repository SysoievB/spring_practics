package com.autowire_require_false;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class AutowireRequireFalseApplication {
    private final ComponentClass componentClass;

    public static void main(String[] args) {
        SpringApplication.run(AutowireRequireFalseApplication.class, args);
    }

    @Bean
    CommandLineRunner startup() {
        return args -> {
            componentClass.helloFromComponentClass();
        };
    }
}
