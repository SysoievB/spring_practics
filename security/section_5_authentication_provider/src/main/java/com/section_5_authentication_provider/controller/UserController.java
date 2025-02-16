package com.section_5_authentication_provider.controller;

import com.section_5_authentication_provider.model.Customer;
import com.section_5_authentication_provider.repository.CustomerRepository;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Objects.isNull;

@Validated
@RestController
public class UserController {
    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public UserController(CustomerRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Nullable Customer customer) {
        if (isNull(customer)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("User registration failed, since customer data not provided");
        }
        if (customer.getEmail().isBlank() || customer.getPassword().isBlank() || customer.getRole().isBlank() || isNull(customer.getBirthday())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("User registration failed, since customer data not valid");
        }
        return Optional.of(customer)
                .filter(c -> (LocalDate.now().getYear() - c.getBirthday().getYear()) >= 18)
                .map(c -> {
                    String hashedPassword = encoder.encode(customer.getPassword());
                    customer.setPassword(hashedPassword);
                    return repository.save(customer);
                })
                .map(__ -> ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered"))
                .orElse(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body("Customer must be at least 18 years old to register"));
    }
}
