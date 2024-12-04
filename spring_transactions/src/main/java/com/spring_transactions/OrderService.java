package com.spring_transactions;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOrderWithNewTransaction() {
        val order = new Order("Laptop", 1200.0);

        if (order.getAmount() > 1000) {// Simulate an error
            throw new RuntimeException("Order amount exceeds limit!");
        }

        orderRepository.save(order);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveOrderSupports() {
        orderRepository.save(new Order("Table", 200.0));
        // If no transaction exists, executes without rollback guarantees
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveOrderNotSupported() {
        val order = new Order("Chair", 150.0);

        if (order.getAmount() > 100) {
            throw new RuntimeException("Order amount too high!");
        }
        orderRepository.save(order);
        // Exception doesn't affect the user's transaction
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveOrderMandatory() {
        orderRepository.save(new Order("Laptop", 1200.0));
        // Throws an exception if called outside a transaction
    }

    @Transactional(propagation = Propagation.NEVER)
    public void saveOrderNever() {
        orderRepository.save(new Order("Sofa", 800.0));
        // Throws an exception if any transaction exists
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveOrderNested() {
        val order = new Order("Tablet", 500.0);

        if (order.getAmount() > 400) {
            throw new RuntimeException("Order amount exceeds nested transaction limit!");
        }
        orderRepository.save(order);
    }

}
