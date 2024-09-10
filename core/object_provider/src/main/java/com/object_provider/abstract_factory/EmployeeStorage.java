package com.object_provider.abstract_factory;

import com.object_provider.Employee;
import com.object_provider.WorkType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeStorage {
    private static final List<Employee> EMPLOYEES = new ArrayList<>();

    static {
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

    public List<Employee> getEmployees() {
        return EMPLOYEES;
    }
}
