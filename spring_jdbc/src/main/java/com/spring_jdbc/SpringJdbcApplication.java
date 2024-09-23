package com.spring_jdbc;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(UserRepositoryImpl repository) {
        return args -> {
            val user1 = new User("John", "Doe", LocalDate.of(1990, 1, 1));
            val user2 = new User("Jane", "Smith", LocalDate.of(1985, 6, 15));
            val user3 = new User("Bob", "Johnson", LocalDate.of(2010, 12, 5));
            val user4 = new User("Jane", "Johnson", LocalDate.of(2010, 12, 5));

            repository.save(user1);
            repository.save(user2);
            repository.save(user3);
            repository.save(user4);

            repository.deleteById(user3.getId());

            repository.findAll().forEach(System.out::println);

            repository.update(user1.getId(), new User("Vasia", "Pupkin", LocalDate.of(2019, 1, 1)));

            System.out.println(repository.findById(30L));

            System.out.println("-------------isAdult--------------");
            repository.findByIsAdult(false).forEach(System.out::println);

            System.out.println("-------------findByNameContaining--------------");
            repository.findByNameContaining("J").forEach(System.out::println);
        };
    }
}
