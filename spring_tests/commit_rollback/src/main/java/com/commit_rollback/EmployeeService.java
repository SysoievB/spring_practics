package com.commit_rollback;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo repository;


    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id=" + id + " not found"));
    }

    @Transactional
    public Optional<Employee> getEmployeeByNameOrCreateIfNotExists(String name) {
        return repository.findByName(name)
                .or(() -> Optional.of(repository.save(new Employee("DefaultName", 18))));
    }
}
