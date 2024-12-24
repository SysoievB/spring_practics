package com.webflux.context;

import reactor.core.publisher.Mono;

public class ContextExpectations {

    public Mono<String> getContextMono() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Hello " + ctx.get("user"));
            }
            return Mono.error(new IllegalStateException("No user in context"));
        });
    }
}

