package com.spring_boot_rest_crud_with_virtual_threads;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ExecutorService executorService;

    List<Car> getCars() {
        try {
            return executorService.submit(carRepository::getCars).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    Car getCarByModel(String model) {
        try {
            return executorService.submit(() -> carRepository.getCarByModel(model)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    Car createCar(Car car) {
        try {
            return executorService.submit(() -> carRepository.createCar(car)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteCarByModel(String model) {
        try {
            executorService.submit(() -> carRepository.deleteCarByModel(model)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
