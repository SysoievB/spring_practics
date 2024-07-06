package com.embedded_id;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @EmbeddedId
    private BookId id;
    private String genre;
    private Integer price;
}
