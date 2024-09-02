package com.object_provider;

import org.springframework.stereotype.Component;

@Component
public class RemoteEmployeeService extends EmployeeService {

    @Override
    public WorkType getWorkType() {
        return WorkType.REMOTE;
    }

    @Override
    public String getEmployeeName(Employee employee) {
        return "Employee " + employee.getName() + " " + employee.getSurname() + " works remotely";
    }
}
