package com.formula_annotation;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FormulaAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormulaAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(EmployeeRepo repo) {
        return arg -> {
            val employee = new Employee(10_000L, 25);
            val saved = repo.save(employee);
            System.out.println(saved);
            //Employee(id=1, grossIncome=10000, taxInPercents=25, tax=0)

            repo.findById(saved.getId())
                    .ifPresent(System.out::println);
            //Employee(id=1, grossIncome=10000, taxInPercents=25, tax=2500)
        };
    }
}
