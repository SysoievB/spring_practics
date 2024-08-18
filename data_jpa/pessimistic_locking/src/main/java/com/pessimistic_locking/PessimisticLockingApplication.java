package com.pessimistic_locking;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  JPA specification provides different types of exceptions:
 *
 * PessimisticLockException indicates that obtaining a lock or converting a shared to exclusive
 * lock fails and results in a transaction-level rollback.
 *
 * LockTimeoutException indicates that obtaining a lock or converting a shared lock to exclusive
 * times out and results in a statement-level rollback.
 *
 * PersistenceException indicates that a persistence problem occurred. PersistenceException and
 * its subtypes, except NoResultException, NonUniqueResultException, LockTimeoutException and
 * QueryTimeoutException, mark the active transaction to be rolled back.*/
@SpringBootApplication
public class PessimisticLockingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessimisticLockingApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(ProductService service) {
        return args -> {
            val product = service.createProduct("Product A", 100.0);

            Runnable task1ReadLock = () ->
                    System.out.println("task1ReadLock" + service.findProductForReading(product.getName()));
            Runnable task2WriteLock = () ->
                    System.out.println("task2WriteLock" + service.updateProduct(product.getId(), 1213).toString());

            val thread1 = new Thread(task1ReadLock);
            val thread2 = new Thread(task2WriteLock);

            thread1.start();
            thread2.start();

           val updatedProduct = service.findProductForReading(product.getName());
           System.out.println("Final product price: " + updatedProduct.getPrice());
        };
    }
}
