package com.spring_jdbc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotNull
    LocalDate dateOfBirth;
    boolean adult;

    public User(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.adult = isAdult(dateOfBirth);
    }

    public User(Long id, String name, String surname, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.adult = isAdult(dateOfBirth);
    }

    public boolean isAdult(LocalDate dateOfBirth) {
        return Optional.ofNullable(dateOfBirth)
                .filter(birth -> (LocalDate.now().getYear() - birth.getYear()) >= 18)
                .isPresent();
    }
}