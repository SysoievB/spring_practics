package com.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Primary
    @Bean
    @Description(value = "Primary bean Cat with name Vaska")
    Cat cat() {
        return new Cat("Vaska");
    }

    @Bean
    Dog dog()  {
        return new Dog("Palkan");
    }
}
