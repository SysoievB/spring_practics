package com.embedded_id;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EmbeddedIdApplication implements CommandLineRunner {
    private final BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedIdApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
