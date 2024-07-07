package com.id_class;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class IdClassApplication implements CommandLineRunner {
    private final BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(IdClassApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val book = new Book("Palanin", "Vasia", "MALE", 100);
        repository.save(book);

        repository.findByAuthor("Palanin")
                .forEach(System.out::println);
        //Book(author=Palanin, name=Vasia, genre=MALE, price=100)
        repository.findByName("Vasia")
                .forEach(System.out::println);
        //Book(author=Palanin, name=Vasia, genre=MALE, price=100)
    }
}
