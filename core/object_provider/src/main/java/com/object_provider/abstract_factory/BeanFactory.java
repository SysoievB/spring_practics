package com.object_provider.abstract_factory;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanFactory {
    private final EmployeeStorage employeeStorage;

    @Bean
    public EmployeeService onsiteEmployeeService() {
        return new OnsiteEmployeeService(employeeStorage);
    }

    @Bean
    public EmployeeService remoteEmployeeService() {
        return new RemoteEmployeeService(employeeStorage);
    }
}
