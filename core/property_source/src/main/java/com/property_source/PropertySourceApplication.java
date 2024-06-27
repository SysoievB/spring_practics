package com.property_source;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class PropertySourceApplication implements CommandLineRunner {
    private final PropertySourceConfig source;


    public static void main(String[] args) {
        SpringApplication.run(PropertySourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(source.getName());
        System.out.println(source.getYear());
    }
}
