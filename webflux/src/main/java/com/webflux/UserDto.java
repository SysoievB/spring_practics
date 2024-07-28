package com.webflux;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

record UserDto(
        @NotBlank String name,
        @Min(value = 18) @Max(value = 80) @NotNull Integer age
) {
}
