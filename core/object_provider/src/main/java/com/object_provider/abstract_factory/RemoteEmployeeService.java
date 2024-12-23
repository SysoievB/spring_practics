package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class RemoteEmployeeService implements EmployeeService {
    private final EmployeeStorage employeeStorage;

    @Override
    public WorkType getWorkType() {
        return WorkType.REMOTE;
    }

    @Override
    public Stream<Employee> getEmployees() {
        return employeeStorage.getEmployees().stream();
    }
}
