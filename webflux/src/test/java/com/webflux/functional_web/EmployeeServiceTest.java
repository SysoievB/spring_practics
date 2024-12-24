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
import reactor.core.publisher.Mono;
import reactor.core.publisher.Operators;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

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

    /**
     * <h2>expectNextMatches</h2> - require a predicate that returns a boolean
     * <h2>enableConditionalSupport</h2> turns the subscriber into a Fuseable.
     * ConditionalSubscriber, which allows conditional processing of emitted elements
     * (e.g., elements satisfying a predicate are processed differently).
     */

    @Test
    void getAllEmployees() {
        // Given
        val employee1 = employee().name("John Doe").age(25).build();
        val employee2 = employee().name("Vasia").age(32).build();

        given(repository.findAll()).willReturn(Flux.just(employee1, employee2));

        // When
        val result = service.getAllEmployees();

        // Then
        StepVerifier.create(result)
                .expectNext(employee1)
                .expectNext(employee2)
                .then(() -> verify(repository, times(1)).findAll())
                .verifyComplete();

        StepVerifier.create(result)
                .enableConditionalSupport(employee -> employee.getName().startsWith("John"))
                .expectNextMatches(employee -> "John Doe".equals(employee.getName()) && employee.getAge() == 25)
                .expectNext(employee2)
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
     */
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

    @Test
    void testFindEmployeeById() {
        // Given
        val id = 100L;
        val name = "John Doe";
        val age = 25;
        val employee1 = employee().id(id).name(name).age(age).build();

        given(repository.findById(anyLong())).willReturn(Mono.just(employee1));

        // When
        val result = service.findEmployeeById(id);

        // Then
        StepVerifier.create(result)
                .consumeNextWith(employee ->
                        assertThat(employee)
                                .returns(id, Employee::getId)
                                .returns(name, Employee::getName)
                                .returns(age, Employee::getAge)
                )
                .verifyComplete();
    }

    @Test
    void testFindEmployeeById_error() {
        // Given
        val id = 100L;

        given(repository.findById(anyLong()))
                .willReturn(Mono.error(new EmployeeService.EmployeeNotFoundException("Employee not found")));

        // When
        val result = service.findEmployeeById(id);

        // Then
        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof EmployeeService.EmployeeNotFoundException &&
                                "Employee not found".equals(throwable.getMessage()))
                .verify();
    }

    @Test
    void operatorThrowsErrorExplicitly() {
        Mono<String> source = Mono.just("test")
                .map(value -> {
                    throw new RuntimeException("Operator error");
                });

        StepVerifier.create(source)
                .expectError()
                .verifyThenAssertThat()
                .hasOperatorErrorOfType(RuntimeException.class)
                .hasOperatorErrorWithMessage("Operator error");
    }

    @Test
    void operatorAssertions() {
        Mono<String> source = Mono.just("test")
                .map(String::toUpperCase);

        StepVerifier.create(source)
                .expectNext("TEST") // Check that the next value is "TEST"
                .expectComplete() // Ensure the stream completes after emitting the value
                .verifyThenAssertThat()
                .hasNotDroppedElements() // Ensure no elements were dropped
                .hasNotDroppedErrors(); // Ensure no errors were dropped
    }

    @Test
    void assertDroppedElementsAllPass() {
        val flux = Flux.from(s -> {
            s.onSubscribe(Operators.emptySubscription());
            s.onNext("foo");
            s.onComplete();
            s.onNext("bar");
            s.onNext("baz");
        }).take(3, false);

        StepVerifier.create(flux)
                .expectNext("foo")
                .expectComplete()
                .verifyThenAssertThat()
                .hasDroppedElements()
                .hasDropped("baz")
                .hasDroppedExactly("baz", "bar");
    }

    @Test
    void createEmployeeTest() {
        val dto = mock(EmployeeDto.class);
        val employee = employee().name("NAME").age(30).build();
        given(repository.save(any())).willReturn(Mono.just(employee));

        val result = service.createEmployee(dto);

        StepVerifier.create(result)
                .assertNext(emp -> assertThat(emp)
                        .usingRecursiveComparison()
                        .isEqualTo(employee))
                .verifyComplete();
    }

    @Test
    void deleteEmployeeTest() {
        // Given
        val probe = PublisherProbe.<Employee>empty();

        given(repository.findById(anyLong())).willReturn(Mono.just(mock(Employee.class)));
        given(repository.delete(any())).willReturn(probe.mono().then());

        // When
        val result = service.deleteEmployee(100L);

        // Then
        StepVerifier.create(result)
                .then(() -> {
                    probe.assertWasRequested();
                    probe.assertWasSubscribed();
                    probe.assertWasNotCancelled();
                })
                .verifyComplete();
    }

    @Test
    void findEmployeeByIdTest() {
        // Given
        val id = 100L;
        val name = "Vasia";
        val age = 23;
        val employee = employee().id(id).name(name).age(age).build();
        val probe = PublisherProbe.of(Mono.just(employee));

        given(repository.findById(anyLong())).willReturn(probe.mono());

        // When
        val result = service.findEmployeeById(id);

        // Then
        StepVerifier.create(result)
                .assertNext(emp -> assertThat(emp)
                        .returns(id, Employee::getId)
                        .returns(name, Employee::getName)
                        .returns(age, Employee::getAge)
                )
                .then(() -> {
                    probe.assertWasRequested();
                    probe.assertWasSubscribed();
                    probe.assertWasNotCancelled();
                })
                .verifyComplete();
    }

    @Builder(builderMethodName = "employee")
    private Employee getEmployee(@Nullable Long id, @Nullable String name, @Nullable Integer age) {
        val employee = mock(Employee.class);
        if (nonNull(id)) {
            given(employee.getId()).willReturn(id);
        }
        if (nonNull(name)) {
            given(employee.getName()).willReturn(name);
        }
        if (nonNull(age)) {
            given(employee.getAge()).willReturn(age);
        }
        return employee;
    }
}