package com.webflux.functional_web;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Configuration
@AllArgsConstructor
public class EmployeeRouter {
    private final EmployeeHandler employeeHandler;

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return RouterFunctions
                .route(GET("/employee"), employeeHandler::getAllEmployees)
                .andRoute(GET("/employee/{id}"), employeeHandler::getEmployeeById)
                .andRoute(POST("/employee"), employeeHandler::createEmployee)
                .andRoute(PUT("/employee/{id}"), employeeHandler::updateEmployee)
                .andRoute(DELETE("/employee/{id}"), employeeHandler::deleteEmployee);
    }
}
