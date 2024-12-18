package com.webmvctest_autoconfiguremockmvc;

import jakarta.validation.constraints.NotNull;

public record CreateCarDto(
        @NotNull String brand,
        @NotNull String model,
        @NotNull String color
) {
}
