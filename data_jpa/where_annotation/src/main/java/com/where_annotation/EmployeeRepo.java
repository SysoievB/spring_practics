package com.where_annotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("from Employee where status = 'EVEN' or status = 'ODD'")
    List<Employee> findAllEmployees();
}
