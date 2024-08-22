package com.web_annotations.map_struct;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeService {
    private EmployeeMapper mapper;

    public Employee getEmployee(EmployeeDTO dto) {
        return mapper.toEmployee(dto);
    }

    public EmployeeDTO getDto(Employee employee) {
        return mapper.toEmployeeDTO(employee);
    }
}
