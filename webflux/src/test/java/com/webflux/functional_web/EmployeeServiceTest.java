package com.webflux.functional_web;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo repository;
    @InjectMocks
    private EmployeeService service;

    @Test
    void getAllEmployees() {
        // Given
        val employee1 = employee().build();
        val employee2 = employee().build();

        given(repository.findAll()).willReturn(Flux.just(employee1, employee2));

        // When
        val result = service.getAllEmployees();

        // Then
        StepVerifier.create(result)
                .expectNext(employee1)
                .expectNext(employee2)
                .then(() -> verify(repository, times(1)).findAll())
                .verifyComplete();
    }

    @Test
    void getAllEmployees_WithOneField() {
        // Given
        val name = "John Doe";
        val age = 25;
        val employee1 = employee().name(name).age(age).build();

        given(repository.findAll()).willReturn(Flux.just(employee1));

        // When
        val result = service.getAllEmployees();

        // Then
        StepVerifier.create(result)
                .enableConditionalSupport(employee -> employee.getName().startsWith("John"))
                .expectNextMatches(employee -> "John Doe".equals(employee.getName()) && employee.getAge() == 25)
                .verifyComplete();

        StepVerifier.create(result)
                .consumeNextWith(employee ->
                        assertThat(employee)
                                .returns(name, Employee::getName)
                                .returns(age, Employee::getAge)
                )
                .verifyComplete();
    }

    /**
     * <h3>What is Reactor Fusion?</h3>
     * Reactor Fusion is an internal mechanism that optimizes how data is passed through reactive streams.
     * It works by fusing multiple operators into a single pipeline, which avoids creating intermediate
     * stages and queues. This optimization can run in different modes:
     * <ul>
     *   <li>SYNC: Synchronous fusion, where data is passed through the pipeline immediately.</li>
     *   <li>ASYNC: Asynchronous fusion, where data is queued and processed later.</li>
     *   <li>ANY: Any type of fusion (either synchronous or asynchronous).</li>
     *   <li>THREAD_BARRIER: Prevents fusion, ensuring operators remain distinct.</li>
     * </ul>
     * */
    @Test
    void testFusion() {
        // Given: A simple publisher with a map operator
        Flux<Integer> source = Flux.range(1, 10).map(i -> i * 2);

        // When
        StepVerifier.create(source)
                .expectFusion()
                .expectNext(2, 4, 6, 8, 10, 12, 14, 16, 18, 20) // Expected transformed values
                .verifyComplete();
    }


    @Builder(builderMethodName = "employee")
    private Employee getEmployee(@Nullable String name, @Nullable Integer age) {
        val employee = mock(Employee.class);
        if (nonNull(name)) {
            given(employee.getName()).willReturn(name);
        }
        if (nonNull(age)) {
            given(employee.getAge()).willReturn(age);
        }
        return employee;
    }
}