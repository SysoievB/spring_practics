package com.inheritance_annotation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InheritanceAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InheritanceAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(UserRepository userRepository) {
        return args -> {
            User user = new User("John Doe");
            Employee employee = new Employee("Jane Doe", "Engineer", 50000);
            Client client = new Client("Client Doe", "123 Main St");

            userRepository.save(user);
            userRepository.save(employee);
            userRepository.save(client);

            userRepository.findAll().forEach(System.out::println);

            //User(id=1, name=John Doe)
            //Employee(occupation=Engineer, salary=50000)
            //Client(address=123 Main St)
        };
    }
}
