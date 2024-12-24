package com.webflux.context;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ContextExpectationsTest {

    @InjectMocks
    private ContextExpectations contextExpectations;

    @Test
    void contextContains() {
        val result = contextExpectations.getContextMono().contextWrite(Context.of("user", "John"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .contains("user", "John")
                .then() // Return to sequence expectations
                .expectNext("Hello John") // Validate the emitted value
                .verifyComplete();
    }

    @Test
    void contextHasKey() {
        val result = contextExpectations.getContextMono().contextWrite(Context.of("user", "John"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .hasKey("user")
                .then()
                .expectNext("Hello John")
                .verifyComplete();
    }

    @Test
    void contextHasCorrectSize() {
        val result = Mono.deferContextual(ctx -> Mono.just("Context has " + ctx.size() + " keys"))
                .contextWrite(Context.of("user", "John", "role", "admin"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .hasSize(2)
                .then()
                .expectNext("Context has 2 keys")
                .verifyComplete();
    }

    @Test
    void contextContainsAllOf() {
        val other = Context.of("user", "John", "role", "admin");

        val result = Mono.deferContextual(ctx -> Mono.just("Valid context"))
                .contextWrite(Context.of("user", "John", "role", "admin", "department", "HR"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .containsAllOf(other)
                .then()
                .expectNext("Valid context")
                .verifyComplete();
    }

    @Test
    void contextContainsOnly() {
        val exactContext = Context.of("user", "John", "role", "admin");

        val result = Mono.deferContextual(ctx -> Mono.just("Exact context match"))
                .contextWrite(Context.of("user", "John", "role", "admin"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .containsOnly(exactContext)
                .then()
                .expectNext("Exact context match")
                .verifyComplete();
    }

    @Test
    void customContextAssertion() {
        Mono<String> result = Mono.deferContextual(ctx -> Mono.just("Custom assertion"))
                .contextWrite(Context.of("user", "John", "role", "admin"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .assertThat(ctx ->
                        assertThat(ctx)
                                .returns("John", c -> c.get("user")))
                .then()
                .expectNext("Custom assertion")
                .verifyComplete();
    }

    @Test
    void contextMatchesPredicate() {
        val result = Mono.deferContextual(ctx -> Mono.just("Context matches predicate"))
                .contextWrite(Context.of("user", "John", "role", "admin"));

        StepVerifier.create(result)
                .expectAccessibleContext()
                .matches(ctx -> ctx.hasKey("user") && "admin".equals(ctx.get("role")))
                .then()// Return to the sequence
                .expectNext("Context matches predicate")
                .verifyComplete();
    }

}