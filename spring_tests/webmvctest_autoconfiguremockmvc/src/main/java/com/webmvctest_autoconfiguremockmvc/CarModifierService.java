package com.webmvctest_autoconfiguremockmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * <h3>Mockito specific methods for testing</h3>*/
@Component
@RequiredArgsConstructor
public class CarModifierService {
    private final CarService carService;

    protected Optional<Car> searchBrand(String searchString) {
        return carService.searchBrand(searchString);
    }

    protected Optional<Car> searchCarByBrand(Car car) {
        return carService.searchCarByBrand(car);
    }

    public static String staticMethod(String name) {
        return "Hello " + name;
    }

    private String privateMethod(String name) {
        return "Hello " + name;
    }

    public String timeDelayed(long seconds) {
        try {
            Thread.sleep(Duration.of(seconds, ChronoUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Seconds amount: " + seconds;
    }
}
