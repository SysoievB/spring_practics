package com.spring_boot_rest_crud_with_virtual_threads;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarRepository {
    private List<Car> cars = new ArrayList<>();

    CarRepository() {
        cars.add(new Car("Toyota Corolla", "1.8L", 2020));
        cars.add(new Car("Ford Mustang", "5.0L V8", 2021));
        cars.add(new Car("Chevrolet Camaro", "3.6L V6", 2019));
        cars.add(new Car("Honda Accord", "2.0L Turbo", 2022));
        cars.add(new Car("Tesla Model S", "Electric", 2021));
        cars.add(new Car("BMW 3 Series", "2.0L Turbo", 2020));
        cars.add(new Car("Audi A4", "2.0L Turbo", 2021));
        cars.add(new Car("Mercedes-Benz C-Class", "2.0L Turbo", 2019));
        cars.add(new Car("Hyundai Sonata", "2.5L", 2020));
        cars.add(new Car("Volkswagen Golf", "1.4L Turbo", 2021));
    }

    List<Car> getCars() {
        return cars;
    }

    Car getCarByModel(String model) {
        return cars.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(model))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No car found with model: " + model));
    }

    Car createCar(Car car) {
        cars.add(car);
        return car;
    }

    void deleteCarByModel(String model) {
        cars.removeIf(car -> car.getModel().equalsIgnoreCase(model));
    }
}
