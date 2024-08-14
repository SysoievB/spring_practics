package com.optimistic_lock_exception;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class OptimisticLockExceptionApplication implements CommandLineRunner {
    private final ProductService service;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockExceptionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val product = service.createProduct("Product A", 100.0);

        Runnable task1 = () -> service.updateProductPrice(product.getId(), 150.0);
        Runnable task2 = () -> service.updateProductPrice(product.getId(), 200.0);

        val thread1 = new Thread(task1);
        val thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        val updatedProduct = service.findProductById(product.getId());
        System.out.println("Final product price: " + updatedProduct.getPrice());
    }
}
//Exception in thread "Thread-7" org.springframework.orm.ObjectOptimisticLockingFailureException:
// Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [com.optimistic_lock_exception.Product#1]