package com.webmvctest_autoconfiguremockmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    List<Car> getCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    Car getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Car createCar(@RequestBody CreateCarDto dto){
        return carService.save(dto);
    }

    @PutMapping("/{id}")
    Car createCar(@PathVariable Long id, @RequestBody CreateCarDto dto){
        return carService.update(id, dto.brand(), dto.model(), dto.color());
    }

    @GetMapping("/ByBrandAndModel")
    Car getCarById(@RequestParam String brand, @RequestParam String model) {
        return carService.findByBrandAndModel(brand, model);
    }

    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable Long id) {
        carService.delete(id);
    }
}
