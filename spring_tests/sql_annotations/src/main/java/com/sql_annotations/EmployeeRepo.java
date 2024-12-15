package com.sql_annotations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByNameIsContainingIgnoreCase(String substring);

    long countAllByName(String name);
}
