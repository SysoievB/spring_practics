package com.web_annotations;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UserDto(
        @NotBlank String name,
        @NotBlank String surname,
        @NotNull @Positive(message = "Salary cannot be negative") Integer salary,
        @Nullable LocalDate birthDate
) {
}
