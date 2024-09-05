package com.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//http://localhost:8080/swagger-ui/index.html
@Tag(name = "Swagger exploration", description = "Swagger personal investigation")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {
    private static List<Book> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new Book(1L, "Hello"));
        BOOKS.add(new Book(4L, "Hello"));
        BOOKS.add(new Book(2L, "3 friends"));
    }

    @Tag(name = "books", description = "Book POST")
    @PostMapping
    ResponseEntity<Book> create(@RequestBody Book book) {
        BOOKS.add(book);
        return ResponseEntity.ok().body(book);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Book.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "The Book with given Id was not found.", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    ResponseEntity<Book> get(@PathVariable Long id) {
        return BOOKS.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Parameters({
            @Parameter(name = "title", description = "Search Book by title"),
            @Parameter(name = "page", description = "Page number, starting from 0", required = true),
            @Parameter(name = "size", description = "Number of items per page", required = true)
    })
    @GetMapping
    ResponseEntity<List<Book>> getAllBooks(
            @Parameter(description = "Search Book by title") @RequestParam(required = false) String title,
            @Parameter(description = "Page number, starting from 0", required = true) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", required = true) @RequestParam(defaultValue = "3") int size) {
        return Optional.ofNullable(title)
                .map(t -> BOOKS.stream()
                        .filter(book -> book.getTitle().equals(t))
                        .toList()
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(BOOKS));
    }

    @Getter
    @Setter(AccessLevel.PACKAGE)
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "A book object")
    static class Book {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Book Id", example = "123")
        Long id;

        @Schema(description = "Book's title", example = "Swagger Tutorial")
        String title;
    }
}
