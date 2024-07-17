package com.specifications;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class SpecificationsApplication {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpecificationsApplication.class, args);
    }

    @Bean
    public CommandLineRunner startUp() {
        return args -> {
            val users = List.of(
                    new User("John", "Doe", "USA", LocalDate.of(1990, 1, 1), 34),
                    new User("John", "Smith", "Canada", LocalDate.of(1995, 2, 2), 29),
                    new User("Alice", "Johnson", "UK", LocalDate.of(2000, 3, 3), 24),
                    new User("Bob", "Williams", "Australia", LocalDate.of(1985, 4, 4), 39),
                    new User("Charlie", "Brown", "Germany", LocalDate.of(1992, 5, 5), 32),
                    new User("Dave", "Davis", "France", LocalDate.of(1988, 6, 6), 36),
                    new User("Eve", "Martinez", "Spain", LocalDate.of(1998, 7, 7), 26),
                    new User("John", "Garcia", "Italy", LocalDate.of(2022, 8, 8), 1),
                    new User("John", "Garcia", "Italy", LocalDate.of(2000, 8, 8), 23),
                    new User("John", "Benson", "USA", LocalDate.of(2012, 8, 8), 11),
                    new User("Sam", "Benson", "USA", LocalDate.of(1990, 8, 8), 33),
                    new User("Sam", "Benson", "USA", LocalDate.of(2012, 8, 8), 11),
                    new User("John", "Miller", "Japan", LocalDate.of(2012, 8, 8), 11),
                    new User("Grace", "Lee", "Japan", LocalDate.of(1994, 9, 9), 30),
                    new User("Hank", "Miller", "China", LocalDate.of(1980, 10, 10), 44)
            );
            repository.saveAll(users);

            repository.getUserDtoByName("John")
                    .forEach(System.out::println);
//User(id=1, name=John, surname=Doe, country=USA, birthDate=1990-01-01, age=34, isAdult=true, createdAt=2024-07-15T18:29:04.575937Z, updatedAt=2024-07-15T18:29:04.575937Z)
//User(id=2, name=John, surname=Smith, country=Canada, birthDate=1995-02-02, age=29, isAdult=true, createdAt=2024-07-15T18:29:04.651937Z, updatedAt=2024-07-15T18:29:04.651937Z)

            repository.findAllWithingAgeRangeInclusive(30, 40)
                    .stream()
                    .map(User::getAge)
                    .forEach(System.out::println);
            //39 36 34 32 30

            repository.findByNameAndSurnameAndAdult("John", "Garcia")
                    .ifPresent(System.out::println);
//User(id=9, name=John, surname=Garcia, country=Italy, birthDate=2000-08-08, age=23, isAdult=true, createdAt=2024-07-16T04:14:50.614836Z, updatedAt=2024-07-16T04:14:50.614836Z)

            repository.findByNameAndCountryAndNotAdult("John", "Italy")
                    .ifPresent(System.out::println);
//User(id=8, name=John, surname=Garcia, country=Italy, birthDate=2022-08-08, age=1, isAdult=false, createdAt=2024-07-16T04:14:50.611833Z, updatedAt=2024-07-16T04:14:50.611833Z)

            repository.findByCountryAndBirthDateBetween("USA", null, null)
                    .forEach(System.out::println);
//User(id=1, name=John, surname=Doe, country=USA, birthDate=1990-01-01, age=34, isAdult=true, createdAt=2024-07-16T04:14:50.497242Z, updatedAt=2024-07-16T04:14:50.498245Z)
//User(id=10, name=John, surname=Benson, country=USA, birthDate=2012-08-08, age=11, isAdult=false, createdAt=2024-07-16T04:14:50.617832Z, updatedAt=2024-07-16T04:14:50.617832Z)

            repository.findByCountryAndBirthDateBetween("USA", LocalDate.parse("1990-01-01"), null)
                    .forEach(System.out::println);

            repository.findByCountryAndBirthDateBetween("USA", LocalDate.parse("1990-01-01"), LocalDate.now())
                    .forEach(System.out::println);
        };
    }
}
