package com.generationtype_sequence;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class GenerationtypeSequenceApplication implements CommandLineRunner {
    private final UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(GenerationtypeSequenceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val user1 = new User("Vasia");
        val user2 = new User("Petia");

        repo.saveAll(List.of(user1, user2))
                .forEach(System.out::println);
        //User(id=1, name=Vasia)
        //User(id=2, name=Petia)
    }
}
