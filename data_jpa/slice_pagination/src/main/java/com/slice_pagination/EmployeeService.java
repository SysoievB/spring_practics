package com.slice_pagination;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<Employee> employeesSlice = employeeRepository.findAllByOrderByIdAsc(pageable);
        return employeesSlice.getContent();
    }
}
