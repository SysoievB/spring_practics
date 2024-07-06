package com.generationtype_custom;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class GenerationtypeCustomApplication implements CommandLineRunner {
    private final UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(GenerationtypeCustomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val user1 = new User("Vasia");
        val user2 = new User("Petia");

        repo.saveAll(List.of(user1, user2))
                .forEach(System.out::println);
        //User(id=cd457450-ae2b-49b5-be09-25f40d5e115f, name=Vasia)
        //User(id=c700f121-7ff8-4aa4-a785-cab0b0fe0da9, name=Petia)
    }
}
