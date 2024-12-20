package com.webmvctest_autoconfiguremockmvc;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cars")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String brand;
    String model;
    String color;

    public Car(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Car update(@Nullable String brand, @Nullable String model, @Nullable String color) {
        if (brand != null) {
            this.brand = brand;
        }
        if (model != null) {
            this.model = model;
        }
        if (color != null) {
            this.color = color;
        }

        return this;
    }
}
