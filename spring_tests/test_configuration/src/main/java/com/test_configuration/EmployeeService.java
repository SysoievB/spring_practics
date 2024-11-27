package com.test_configuration;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    public String findEmployeeById(int id) {
        return "Employee" + id;
    }
}