package com.web_annotations.map_struct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "name", source = "employee.name")
    @Mapping(target = "surname", source = "employee.surname")
    EmployeeDTO toEmployeeDTO(Employee employee);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "surname", source = "dto.surname")
    Employee toEmployee(EmployeeDTO dto);
}