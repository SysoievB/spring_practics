package com.section_5_authentication_provider.controller;

import com.section_4_managing_users_with_db.model.Customer;
import com.section_4_managing_users_with_db.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public UserController(CustomerRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        return Optional.of(customer)
                .map(c -> {
                    String hashedPassword = encoder.encode(customer.getPassword());
                    customer.setPassword(hashedPassword);
                    return repository.save(customer);
                })
                .map(__ -> ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered"))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed"));
    }
}
