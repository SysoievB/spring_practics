package com.web_headers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matrix")
public class MatrixController {
    private final List<Car> CARS;

    {
        CARS = List.of(
                new Car("Toyota Camry", "Red"),
                new Car("Honda Civic", "Blue"),
                new Car("Ford Mustang", "Black"),
                new Car("Chevrolet Impala", "Red"),
                new Car("Nissan Altima", "Green"),
                new Car("BMW 3 Series", "Red"),
                new Car("Audi A4", "White"),
                new Car("Mercedes-Benz C-Class", "Blue"),
                new Car("Honda Civic", "Silver"),
                new Car("Hyundai Sonata", "Black")
        );
    }

    @GetMapping("/color")
    ResponseEntity<List<Car>> getAllCarsFilteredByColor(
            @MatrixVariable(required = false) String color) {
        if (color == null) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }
        return Optional.of(CARS)
                .filter(cars -> cars.stream().allMatch(car -> car.getColor().equalsIgnoreCase(color)))
                .map(list -> new ResponseEntity<>(list, HttpStatus.OK))
                .orElse(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
    }


    @Data
    @AllArgsConstructor
    static class Car {
        String name;
        String color;
    }
}