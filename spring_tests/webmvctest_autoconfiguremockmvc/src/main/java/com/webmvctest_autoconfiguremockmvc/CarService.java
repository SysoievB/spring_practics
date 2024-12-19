package com.webmvctest_autoconfiguremockmvc;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public List<Car> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(),
                        false)//is used in parallel
                .collect(Collectors.toList());
    }

    public Car findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with ID=" + id + " not found"));
    }

    public Car save(CreateCarDto dto) {
        return repository.save(new Car(dto.brand(), dto.color(), dto.model()));
    }

    public void delete(Long id) {
        Optional.of(id)
                .flatMap(repository::findById)
                .ifPresent(repository::delete);
    }

    public Car update(Long id, @Nullable String brand, @Nullable String model, @Nullable String color) {
        return Stream.of(brand, model, color)
                .anyMatch(Objects::nonNull)
                ? repository.findById(id)
                .map(car -> repository.save(car.update(brand, model, color)))
                .orElseThrow(() -> new CarNotFoundException("Car with ID=" + id + " not found"))
                : findById(id);
    }

    public Car findByBrandAndModel(String brand, String model) {
        return repository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new CarNotFoundException("Car with Brand=" + brand + " or Model=" + model + " not found"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CarNotFoundException extends RuntimeException {
        public CarNotFoundException(String message) {
            super(message);
        }
    }
}
