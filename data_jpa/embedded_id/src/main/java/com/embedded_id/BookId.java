package com.embedded_id;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookId implements Serializable {

    private String author;
    private String name;
}
