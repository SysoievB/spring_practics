package com.liquibase;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "book")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "book_uuid", columnDefinition = "CHAR(36)")
    UUID bookUuid;

    @Column(name = "book_name")
    String bookName;

    String title;

    String author;

    String publisher;

    @Column(name = "is_last")
    boolean last;
}
