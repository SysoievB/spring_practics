package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeTrigger {
    @Qualifier("onsiteEmployeeService")
    private final EmployeeService onsiteEmployeeService;

    @Qualifier("remoteEmployeeService")
    private final EmployeeService remoteEmployeeService;

    public Employee getFirstEmployeeByWorkType(WorkType workType) {
        return workType == WorkType.ONSITE
                ? onsiteEmployeeService.getFirstEmployeeByWorkType()
                : remoteEmployeeService.getFirstEmployeeByWorkType();
    }
}
