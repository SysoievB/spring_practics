package com.web_annotations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {
    private static long increment = 1;
    Long id;
    @Setter
    String name;
    String surname;
    int age;
    LocalDate birthDate;
    int salary;

    public User(String name, String surname, int salary, LocalDate birthDate) {
        this.id = increment++;
        this.name = name;
        this.surname = surname;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public User update(String name, String surname, int salary, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
        this.birthDate = birthDate;
        return this;
    }
}
