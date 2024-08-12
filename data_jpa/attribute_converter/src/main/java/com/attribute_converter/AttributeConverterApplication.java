package com.attribute_converter;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AttributeConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttributeConverterApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmailRepo repo) {
        return args -> {
            val email = new Email("Hello, how are you?", "Vasia Pupkin");
            System.out.println(email);
            System.out.println(repo.save(email));
        };
    }

}
