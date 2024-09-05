package com.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Swagger exploration", description = "Swagger personal investigation")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    //@GetMapping


    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "A book object")
    static class Book {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Book Id", example = "123")
        long id = 0;

        @Schema(description = "Book's title", example = "Swagger Tutorial")
        String title;
    }
}
