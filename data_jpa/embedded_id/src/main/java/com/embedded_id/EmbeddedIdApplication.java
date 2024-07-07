package com.embedded_id;

import lombok.AllArgsConstructor;
import lombok.val;
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
        val bookId = new BookId("Palanin", "Vasia");

        val book = new Book(bookId, "MALE", 100);
        repository.save(book);

        repository.findByIdAuthor("Palanin")
                .forEach(System.out::println);
        //Book(id=BookId(author=Palanin, name=Vasia), genre=MALE, price=100)
        repository.findByIdName("Vasia")
                .forEach(System.out::println);
        //Book(id=BookId(author=Palanin, name=Vasia), genre=MALE, price=100)
    }
}
