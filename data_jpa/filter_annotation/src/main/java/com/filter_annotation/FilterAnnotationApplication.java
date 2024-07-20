package com.filter_annotation;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.val;
import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class FilterAnnotationApplication {
    private final EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(FilterAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(EmployeeRepo repo) {
        return args -> {
            int limit = 1000;

            val employees = List.of(
                    new Employee(1L, "Alice", 500, limit),
                    new Employee(2L, "Bob", 1200, limit),
                    new Employee(3L, "Charlie", 1000, limit),
                    new Employee(4L, "David", 1300, limit),
                    new Employee(5L, "Eve", 900, limit)
            );

            repo.saveAll(employees);

            Session session = entityManager.unwrap(Session.class);
            session.clear();

            // Apply the filter
            session.enableFilter("incomeLevelFilter").setParameter("incomeLimit", limit);

            session.createQuery("from Employee", Employee.class).getResultList()
                    .forEach(System.out::println);
        };
    }
}
