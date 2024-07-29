package com.webflux.functional_web;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EmployeeRepo extends R2dbcRepository<Employee, Long> {
}
