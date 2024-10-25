package com.spring_boot_rest_crud_with_virtual_threads;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/{model}")
    public Car getCarByModel(@PathVariable String model) {
        return carService.getCarByModel(model);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @DeleteMapping
    public void deleteCarByModel(@RequestParam String model) {
        carService.deleteCarByModel(model);
    }

    @GetMapping("/thread-type")
    public String getThreadType() {
        val threadName = Thread.currentThread().getName();
        return Thread.currentThread().isVirtual()
                ? threadName + " is virtual"
                : threadName + " is regular";
    }
}
