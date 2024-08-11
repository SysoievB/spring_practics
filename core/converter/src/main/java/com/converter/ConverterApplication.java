package com.converter;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(UserConverter converter) {
        return args -> {
            val user = new User(
                    1L,
                    "user123",
                    "verySecurePassword",
                    "Vasia Pupkin",
                    LocalDate.now()
            );
			System.out.println(user);

			System.out.println(converter.convert(user));
            //User(id=1, username=user123, password=verySecurePassword, fullName=Vasia Pupkin, lastLogin=2024-08-11)
            //UserDTO[username=user123, password=dmVyeVNlY3VyZVBhc3N3b3Jk]
		};
    }
}
