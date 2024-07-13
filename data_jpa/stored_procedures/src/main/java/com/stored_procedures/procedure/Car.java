package com.stored_procedures.procedure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Id;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private Integer year;

    public Car(String model, Integer year) {
        this.model = model;
        this.year = year;
    }
}
