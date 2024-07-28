package com.webflux;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public Mono<List<User>> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getProductById(@PathVariable Long id) {
        return service.findUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<User> createProduct(@RequestBody UserDto dto) {
        return service.createUser(dto);
    }

    @PutMapping("/{id}")
    public Mono<User> updateProduct(@PathVariable Long id, @RequestBody UserDto dto) {
        return service.updateUser(dto, id);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id) {
        return service.deleteUser(id);
    }
}
