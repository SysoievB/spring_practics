package com.quailifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Cat cat() {
        return new Cat("Vaska");
    }

    @Bean
    Dog dog()  {
        return new Dog("Palkan");
    }
}
