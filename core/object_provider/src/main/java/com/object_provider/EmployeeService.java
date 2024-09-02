package com.object_provider;

import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeService {
    private final List<Employee> EMPLOYEES = new ArrayList<>();

    {
        EMPLOYEES.add(new Employee("Alice", "Smith", 30, 5, WorkType.REMOTE));
        EMPLOYEES.add(new Employee("Bob", "Johnson", 45, 20, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("Charlie", "Brown", 28, 3, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("Diana", "Davis", 35, 10, WorkType.REMOTE));
        EMPLOYEES.add(new Employee("Ethan", "Wilson", 40, 15, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("Fiona", "Miller", 25, 2, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("George", "Moore", 50, 25, WorkType.REMOTE));
        EMPLOYEES.add(new Employee("Hannah", "Taylor", 32, 8, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("Ian", "Anderson", 29, 4, WorkType.ONSITE));
        EMPLOYEES.add(new Employee("Jack", "Thomas", 38, 12, WorkType.REMOTE));
    }

    public abstract WorkType getWorkType();

    public abstract String getEmployeeName(Employee employee);

    public Employee getFirstEmployeeByWorkType() {
        return EMPLOYEES.stream()
                .filter(employee -> employee.getType().equals(getWorkType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee with this type is absent"));
    }
}
