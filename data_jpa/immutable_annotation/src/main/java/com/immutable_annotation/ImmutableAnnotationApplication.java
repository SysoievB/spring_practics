package com.immutable_annotation;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImmutableAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmutableAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(AddressRepo repo) {
        return args -> {
            val address = repo.findById(1L);
            address.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Address with id = 1L not present")
            );
            //Address(id=1, street=Main Street, city=New York, flatNumber=1)

            address.map(a -> {
                        a.setCity("New City");
                        return a;
                    })
                    .map(repo::save)
                    .ifPresent(System.out::println);
            //Address(id=1, street=Main Street, city=New York, flatNumber=1)
        };
    }
}
