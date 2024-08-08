package com.specifications;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;
    @Nullable String country;
    LocalDate birthDate;
    int age;
    boolean isAdult;

    @CreationTimestamp
    ZonedDateTime createdAt;

    @UpdateTimestamp
    ZonedDateTime updatedAt;

    public User(String name, String surname, @Nullable String country, LocalDate birthDate, int age) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.birthDate = birthDate;
        this.age = age;
        this.isAdult = age >= 18;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}
