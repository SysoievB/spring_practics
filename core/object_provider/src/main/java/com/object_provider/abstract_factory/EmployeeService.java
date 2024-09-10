package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;

import java.util.stream.Stream;

public interface EmployeeService {

    WorkType getWorkType();

    Stream<Employee> getEmployees();

    default String getEmployeeName(Employee employee) {
        return employee.getName();
    }

    default Employee getFirstEmployeeByWorkType() {
        return getEmployees()
                .filter(employee -> employee.getType().equals(getWorkType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee with this type is absent"));
    }
}
