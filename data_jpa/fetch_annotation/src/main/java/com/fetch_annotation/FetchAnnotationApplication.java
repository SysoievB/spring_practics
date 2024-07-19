package com.fetch_annotation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FetchAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchAnnotationApplication.class, args);
    }

    @Bean
    CommandLineRunner startup(PersonRepository personRepository, OrderRepository orderRepository) {
        return args -> {
            // Create persons
            Person person1 = new Person(null, "John Doe", null);
            Person person2 = new Person(null, "Jane Smith", null);
            personRepository.saveAll(List.of(person1, person2));

            // Create orders
            Order order1 = new Order(null, "Order1", person1);
            Order order2 = new Order(null, "Order2", person1);
            Order order3 = new Order(null, "Order3", person2);
            orderRepository.saveAll(List.of(order1, order2, order3));

            // Fetch and print orders with batch size of 3
            List<Order> orders = orderRepository.findAll();
            orders.forEach(System.out::println);
        };
    }
}