package com.slice_pagination;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SlicePaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlicePaginationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(EmployeeService service) {
        return args -> {
            service.getEmployeesPage(1, 2)
                    .forEach(System.out::println);
        };
        //Employee(id=3, name=Charlie, salary=60000, hireDate=2021-03-25)
        //Employee(id=4, name=David, salary=65000, hireDate=2018-04-30)
    }
}
