package com.web_annotations;

import java.time.LocalDate;

public record UserDto(String name, String surname, int salary, LocalDate birthDate) {
}
