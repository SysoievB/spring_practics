package com.spring_transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    /**
     * REQUIRED is the default propagation type in Spring, and it includes all other transactional
     * methods that are called after it. This means that it is required to create one session for
     * all transactions that are called after it.
     *
     * For instance, you want to save 20 lines of data. On the 18th line, the application throws
     * an error, and the transaction calls the rollback function. Required is the most usage type.
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void createUserWithOrder() {
        userRepository.save(new User("John Doe", "john@example.com"));

        try {
            orderService.createOrderWithNewTransaction();
        } catch (Exception e) {
            System.out.println("Order creation failed: " + e.getMessage());
        }
        // If order creation fails, user creation will still be committed due to REQUIRES_NEW in OrderService
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void supportsPropagation() {
        userRepository.save(new User("Charlie", "charlie@example.com"));
        orderService.saveOrderSupports(); // Uses the existing transaction
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void notSupportedPropagation() {
        userRepository.save(new User("Dave", "dave@example.com"));

        orderService.saveOrderNotSupported();
        // User transaction is not affected by order execution
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void mandatoryPropagation() {
        userRepository.save(new User("Eve", "eve@example.com"));

        orderService.saveOrderMandatory();
    }

    public void neverPropagation() {
        orderService.saveOrderNever(); // Must be called outside any transaction
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void nestedPropagation() {
        userRepository.save(new User("Frank", "frank@example.com"));

        try {
            orderService.saveOrderNested();
        } catch (Exception e) {
            System.out.println("Nested transaction failed: " + e.getMessage());
        }
        // User transaction is not rolled back if the nested transaction fails
    }

}

