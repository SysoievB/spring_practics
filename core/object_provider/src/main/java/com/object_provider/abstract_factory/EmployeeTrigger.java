package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeTrigger {
    private final ObjectProvider<EmployeeService> employeeServices;

    public Employee getFirstEmployeeByWorkType(WorkType workType) {
        return employeeServices.getObject(workType).getFirstEmployeeByWorkType();
    }
}
