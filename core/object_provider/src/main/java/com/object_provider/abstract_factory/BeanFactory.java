package com.object_provider.abstract_factory;

import com.object_provider.WorkType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanFactory {
    private final EmployeeStorage employeeStorage;

    @Bean
    public EmployeeService createEmployeeService(WorkType workType) {
        return WorkType.ONSITE == workType
                ? new OnsiteEmployeeService(employeeStorage)
                : new RemoteEmployeeService(employeeStorage);
    }
}
