package com.quailifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuailifierApplication implements CommandLineRunner {
    private final Animal animal;

    public QuailifierApplication(@Qualifier("cat") Animal animal) {
        this.animal = animal;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuailifierApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        animal.eat();
    }
}
