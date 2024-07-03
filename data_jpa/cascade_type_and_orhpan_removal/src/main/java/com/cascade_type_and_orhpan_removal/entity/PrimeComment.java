package com.cascade_type_and_orhpan_removal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "prime_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PrimeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public PrimeComment(String description) {
        this.description = description;
    }
}
