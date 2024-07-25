package com.slice_pagination;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Slice<Employee> findAllByOrderByIdAsc(Pageable pageable);
}
