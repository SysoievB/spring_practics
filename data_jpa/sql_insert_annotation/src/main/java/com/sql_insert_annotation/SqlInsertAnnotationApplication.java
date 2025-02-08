package com.sql_insert_annotation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqlInsertAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlInsertAnnotationApplication.class, args);
	}

	@Bean
	CommandLineRunner startUp(EmployeeRepository repository) {
		return args -> {
			repository.save(new Employee("Vasia", 3000.0));

			repository.findAll().forEach(System.out::println);
			//Employee{id=1, name='Vasia', salary=3000.0, createdAt=2025-02-08T05:56:40}
		};
	}
}
