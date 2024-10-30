package com.aop.aspect;

import com.aop.model.Car;
import com.aop.model.Vehiclable;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCarAspectTest {

    @InjectMocks
    private UpdateCarAspect updateCarAspect;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Test
    void updateOnSingle_shouldSetAutoTrue_whenReturningMonoOfVehiclable() throws Throwable {
        // Arrange
        Car car = new Car(1, "Car A", 2020);
        Mono<Car> carMono = Mono.just(car);
        when(proceedingJoinPoint.proceed()).thenReturn(carMono);

        // Act
        val resultMono = updateCarAspect.updateOnSingle(proceedingJoinPoint);

        // Assert
        StepVerifier.create(resultMono)
                .expectNextMatches(Vehiclable::isAuto)
                .verifyComplete();

        // Verify that proceed was called only once
        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    void updateOnCollection_shouldSetAutoTrue_whenReturningMonoOfListOfVehiclable() throws Throwable {
        // Arrange
        Car car1 = new Car(1, "Car A", 2020);
        Car car2 = new Car(2, "Car B", 2021);
        Mono<List<Vehiclable>> carsMono = Mono.just(List.of(car1, car2));
        when(proceedingJoinPoint.proceed()).thenReturn(carsMono);

        // Act
        Mono<? extends Iterable<? extends Vehiclable>> resultMono = updateCarAspect.updateOnCollection(proceedingJoinPoint);

        // Assert
        StepVerifier.create(resultMono)
                .expectNextMatches(cars -> {
                    return cars instanceof List<?> &&
                            ((List<?>) cars).stream()
                                    .allMatch(vehicle -> ((Vehiclable) vehicle).isAuto());
                })
                .verifyComplete();

        // Verify that proceed was called only once
        verify(proceedingJoinPoint, times(1)).proceed();
    }
}