package com.commit_rollback;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    private final EmployeeRepo repository;

    public EmployeeService(EmployeeRepo repository) {
        this.repository = repository;
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id=" + id + " not found"));
    }

    @Transactional
    public Employee getEmployeeByNameOrCreateIfNotExists(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(new Employee(name, 18)));
    }
}
