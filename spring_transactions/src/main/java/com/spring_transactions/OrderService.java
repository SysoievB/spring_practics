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

    /**
     * REQUIRES_NEW This propagation type is significantly useful for most cases. If a session is
     * initiated before REQUIRES_NEW, it suspends the previous session and creates a new one.
     * The previous transaction cannot impact this. It means that if an error occurs in a previous
     * transaction, REQUIRES_NEW will not be impacted by this error.
     * */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOrderWithNewTransaction() {
        val order = new Order("Laptop", 1200.0);

        if (order.getAmount() > 1000) {// Simulate an error
            throw new RuntimeException("Order amount exceeds limit!");
        }

        orderRepository.save(order);
    }

    /**
     * If a transaction is started before the SUPPORTS transactional type, It gets involved in the
     * previous transaction. Otherwise, it continues non-transactional.
     * */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveOrderSupports() {
        orderRepository.save(new Order("Table", 200.0));
        // If no transaction exists, executes without rollback guarantees
    }

    /**
     * NOT_SUPPORTED from the point of call suspend all transactions and continue its way non-transactional.
     *
     * When you have methods that only read data from the database but don't modify it, you can use
     * NOT_SUPPORTED. It allows these methods to run without a transaction, potentially improving
     * performance.
     * */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveOrderNotSupported() {
        val order = new Order("Chair", 150.0);

        if (order.getAmount() > 100) {
            throw new RuntimeException("Order amount too high!");
        }
        orderRepository.save(order);
        // Exception doesn't affect the user's transaction
    }

    /**
     * A transaction must be included before it. Otherwise, it throws an error. Always behaves like
     * REQUIRED. When you want to ensure that a specific method is always executed within the context
     * of a transaction, it can be crucial for maintaining data consistency and integrity. You can
     * use of this such this scenario, for instance, payment. You want to ensure a payment method
     * must be included in a transaction.
     * */
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveOrderMandatory() {
        orderRepository.save(new Order("Laptop", 1200.0));
        // Throws an exception if called outside a transaction
    }

    /**
     * If a transaction is started before NEVER, It throws an exception. That means NEVER should use
     * an independent process. You must use it outside any transactional context.
     * */
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
