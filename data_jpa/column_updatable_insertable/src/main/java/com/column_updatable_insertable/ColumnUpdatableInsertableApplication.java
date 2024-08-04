package com.column_updatable_insertable;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ColumnUpdatableInsertableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColumnUpdatableInsertableApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(ProductRepo repo) {
        return args -> {
            val product = new Product(
                    "P001",
                    "Example Brand",
                    new BigDecimal("123.45")
            );
            System.out.println("---saved product---");
            val saved = repo.save(product);
            System.out.println(saved);
//Product(id=5, code=P001, brand=Example Brand, createdAt=2024-08-04T11:25:29.469081100, updatedAt=2024-08-04T11:25:29.469081100, price=123.45)

            System.out.println("---updated product---");
            val updated = repo.findById(saved.getId())
                    .map(prod -> {
                        prod.setCode("new code");
                        prod.setBrand("new brand");

                        return prod;
                    })
                    .map(repo::save);


            System.out.println(repo.findById(updated.get().getId()).get());
//Product(id=5, code=P001, brand=new brand, createdAt=2024-08-04T11:25:29.469081, updatedAt=2024-08-04T11:25:29.469081, price=123)
        };
    }
}
