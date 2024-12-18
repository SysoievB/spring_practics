package com.webmvctest_autoconfiguremockmvc;

import jakarta.annotation.Nullable;

public record UpdateCarDto(
        @Nullable String brand,
        @Nullable String model,
        @Nullable String color
) {
}
