package com.column_updatable_insertable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Defining insertable=false, updatable=false is useful when you need to map a field more
 * than once in an entity, typically:
 * - when using a composite key
 * - when using a shared primary key
 * - when using cascaded primary keys
 * */

@Entity
@Table(name = "products")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(updatable = false, unique = true, nullable = false, length = 10)
    String code;

    @Column(nullable = false, length = 50)//default length is 255
    String brand;

    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    LocalDateTime updatedAt;

    /**
     * For a price field with precision = 5 and scale = 2:
     * The largest value you could store would be 999.99 (5 digits total, with 2 digits after the decimal point).
     * The smallest value would be -999.99.*/
    @Column(precision = 5, scale = 2)
    Double price;
}
