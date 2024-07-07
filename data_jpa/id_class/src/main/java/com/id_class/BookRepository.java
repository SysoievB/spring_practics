package com.id_class;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Book.BookId> {

    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
}
