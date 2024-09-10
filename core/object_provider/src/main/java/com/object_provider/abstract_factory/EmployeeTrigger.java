package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmployeeTrigger {
    private final Map<WorkType, EmployeeFactory> employeeFactories;

    public EmployeeTrigger(EmployeeOnsiteFactory onsiteFactory, EmployeeRemoteFactory remoteFactory) {
        this.employeeFactories = Map.of(
                WorkType.ONSITE, onsiteFactory,
                WorkType.REMOTE, remoteFactory
        );
    }

    public Employee getFirstEmployeeByWorkType(WorkType workType) {
        EmployeeFactory factory = employeeFactories.get(workType);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported WorkType: " + workType);
        }
        return factory.createEmployeeService().getFirstEmployeeByWorkType();
    }
}
