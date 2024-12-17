package com.webmvctest_autoconfiguremockmvc;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByBrandAndModel(String brand, String model);
}
