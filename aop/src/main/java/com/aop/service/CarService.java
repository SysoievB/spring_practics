package com.aop.service;

import com.aop.model.Car;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CarService {

    public Mono<Car> getCarById(Integer id) {
        return getCars()
                .map(cars -> cars.stream().filter(car -> car.getId().equals(id)).findFirst().get());
    }

    public Mono<List<Car>> getCars() {
        return Mono.fromCallable(() -> List.of(
                new Car(1, "BMW", 2020),
                new Car(2, "KIA", 2010),
                new Car(3, "YONG", 2000)
        ));
    }
}
