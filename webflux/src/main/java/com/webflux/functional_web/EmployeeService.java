package com.webflux.functional_web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepo repo;

    Flux<Employee> getAllEmployees() {
        return repo.findAll();
    }

    Mono<Employee> findEmployeeById(Long id) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee not found")));
    }

    @Transactional
    Mono<Employee> updateEmployee(EmployeeDto dto, Long id) {
        return findEmployeeById(id)
                .flatMap(employee -> {
                    employee.setName(dto.name());
                    employee.setAge(dto.age());
                    return repo.save(employee);
                })
                .switchIfEmpty(repo.save(new Employee(id, dto.name(), dto.age())));
    }

    @Transactional
    Mono<Employee> createEmployee(EmployeeDto dto) {
        return repo.save(new Employee(dto.name(), dto.age()));
    }

    @Transactional
    Mono<Void> deleteEmployee(Long id) {
        return findEmployeeById(id)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee not found with id: " + id)))
                .flatMap(repo::delete);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @NoArgsConstructor
    static class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }
}
