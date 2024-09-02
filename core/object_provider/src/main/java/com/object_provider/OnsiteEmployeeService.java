package com.object_provider;

import org.springframework.stereotype.Component;

@Component
public class OnsiteEmployeeService extends EmployeeService {

    @Override
    public WorkType getWorkType() {
        return WorkType.ONSITE;
    }

    @Override
    public String getEmployeeName(Employee employee) {
        return "Employee " + employee.getName() + " " + employee.getSurname() + " works onsite";
    }
}
