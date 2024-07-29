package com.webflux.functional_web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EmployeeHandler {
    private final EmployeeService service;

    public Mono<ServerResponse> getAllEmployees(ServerRequest request) {
        return ServerResponse.ok().body(service.getAllEmployees(), Employee.class);
    }

    public Mono<ServerResponse> getEmployeeById(ServerRequest request) {
        return service.findEmployeeById(Long.valueOf(request.pathVariable("id")))
                .flatMap(employee -> ServerResponse.ok().bodyValue(employee))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createEmployee(ServerRequest request) {
        return request.bodyToMono(EmployeeDto.class)
                .flatMap(service::createEmployee)
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved));
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest request) {
        return request.bodyToMono(EmployeeDto.class)
                .flatMap(employeeDto -> service.updateEmployee(employeeDto, Long.valueOf(request.pathVariable("id"))))
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
        return service.deleteEmployee(Long.valueOf(request.pathVariable("id")))
                .then(ServerResponse.ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
