package com.object_provider.abstract_factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeRemoteFactory implements EmployeeFactory {
    private final EmployeeStorage employeeStorage;

    @Override
    public EmployeeService createEmployeeService() {
        return new RemoteEmployeeService(employeeStorage);
    }
}