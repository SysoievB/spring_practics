package com.sql_insert_annotation;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLInsert(sql = "INSERT INTO employees (id, name, salary, created_at) VALUES (?, ?, ?, NOW())")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    Double salary;

    @Column(name = "created_at", updatable = false)
    java.time.LocalDateTime createdAt;

    public Employee() {
    }

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                '}';
    }
}
