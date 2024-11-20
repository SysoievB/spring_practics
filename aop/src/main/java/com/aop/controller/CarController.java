package com.aop.controller;

import com.aop.aop_classes.BarDao;
import com.aop.aspect.UpdateCar;
import com.aop.model.Car;
import com.aop.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;
    private final BarDao barDao;

    @UpdateCar
    @GetMapping("/{id}")
    Mono<Car> getCar(@PathVariable int id) {
        return service.getCarById(id);
    }

    @UpdateCar
    @GetMapping
    Mono<List<Car>> getCars() {
        return service.getCars();
    }

    @GetMapping("/bar")
    Mono<String> getBar() {
        return Mono.just(barDao.bar());
    }
}
