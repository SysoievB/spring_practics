package com.webmvctest_autoconfiguremockmvc;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.webmvctest_autoconfiguremockmvc.CarModifierService.staticMethod;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class CarModifierMockitoTest {

    @Mock
    private CarService repository;
    @InjectMocks
    private CarModifierService service;

    /**
     * If we upgrade to version 2, we could drop the when(…).thenReturn(…) call,
     * because now Mockito will return an empty Stream on mocked methods by default
     */
    @Test
    void searchBrand_returns_found_car() {
        val car = new Car("BMW", "X5", "Black");
        Car car2 = new Car("Audi", "A4", "White");
        given(repository.searchBrand(eq("BMW"))).willReturn(Optional.of(car));

        val result = service.searchBrand("BMW");

        assertThat(result.get())
                .isEqualTo(car);
    }

    @Test
    void searchCarByBrand_returns_found_car() {
        val car = new Car("BMW", "X5", "Black");
        given(repository.searchCarByBrand(argThat(c -> c.getBrand().equals("BMW"))))
                .willReturn(Optional.of(car));

        val result = service.searchCarByBrand(car);

        assertThat(result.get())
                .isEqualTo(car);
    }

    @Test
    void searchCarByBrand_returns_found_car_then() {
        val car = new Car("BMW", "X5", "Black");
        given(repository.searchCarByBrand(any(Car.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        val result = service.searchCarByBrand(car);

        assertThat(result.get()).isNotNull();
    }

    /**
     * <h3>Mocking Static Method</h3>
     */
    @Test
    void testStaticMethodMock() {
        try (val staticMock = mockStatic(CarModifierService.class)) {
            val name = "Vasia";
            staticMock.when(() -> staticMethod(name)).thenReturn("Hello Vasia");

            assertEquals("Hello Vasia", CarModifierService.staticMethod(name));
        }
    }

    /**
     * <h3>Mocking Constructor</h3>
     */
    @Test
    void testConstructorMock() {
        try (val mocked = mockConstruction(Car.class,
                (mock, context) -> {
                    given(mock.getBrand()).willReturn("BMW");
                    given(mock.getModel()).willReturn("X5");
                    given(mock.getColor()).willReturn("Black");
                })) {

            // Create an instance (it will be mocked)
            Car car = new Car("SomeBrand", "SomeModel", "SomeColor");

            // Verify the mocked behavior
            assertThat(car)
                    .satisfies(c -> {
                        assertThat(c.getBrand()).isEqualTo("BMW");
                        assertThat(c.getModel()).isEqualTo("X5");
                        assertThat(c.getColor()).isEqualTo("Black");
                    });

            // Assert on the number of mock instances created
            assertThat(mocked.constructed()).hasSize(1);
            assertThat(mocked.constructed()).first().isSameAs(car);
        }
    }

    /**
     * <h3>Awaitility</h3>*/
    @Test
    void testAwaitility() {
        given(service.timeDelayed(2)).willAnswer(invocation -> {
            await().pollDelay(2, TimeUnit.SECONDS).until(() -> true);
            return "Seconds amount: 2";
        });

        // Measure the execution time of the mocked method
        long startTime = System.currentTimeMillis();
        String result = service.timeDelayed(2);
        long endTime = System.currentTimeMillis();

        assertEquals("Seconds amount: 2", result);
        assertTrue((endTime - startTime) >= 2000, "Expected delay of at least 2000ms");
    }

    @Test
    void testPrivateMethod() {
        val method = ReflectionUtils
                .findMethod(CarModifierService.class, "privateMethod", String.class)
                .get();
        method.setAccessible(true);

        val result = ReflectionUtils.invokeMethod(method, service, "John");

        assertEquals("Hello John", result);
    }
}

