package com.where_annotation;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WhereAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(EmployeeRepo repo) {
        return args -> {
            val employees = List.of(
                    new Employee(1L, "Alice"),
                    new Employee(2L, "Bob"),
                    new Employee(3L, "Charlie"),
                    new Employee(4L, "David"),
                    new Employee(5L, "Eve")
            );

            repo.saveAll(employees);

            repo.findAll().forEach(System.out::println);
            //Employee(id=2, name=Bob, status=EVEN)
            //Employee(id=4, name=David, status=EVEN)

            System.out.println("All Employees: ");
            repo.findAllEmployees()
                    .forEach(System.out::println);
            //Employee(id=2, name=Bob, status=EVEN)
            //Employee(id=4, name=David, status=EVEN)
        };
    }
}
