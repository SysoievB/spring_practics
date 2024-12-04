package com.spring_transactions;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * <h6>ISOLATION LEVEL DEFAULT</h6>
     * Uses the default isolation level of the underlying database.
     * Typically depends on the database configuration (e.g., MySQL defaults to REPEATABLE_READ).*/
    @Transactional(isolation = Isolation.DEFAULT)
    public void defaultIsolationExample() {
        userRepository.save(new User("Default User", "default@example.com"));
    }

    /**
     * <h6>ISOLATION LEVEL READ_UNCOMMITTED</h6>
     * Allows dirty reads, meaning one transaction can read uncommitted changes from another.
     * Lowest level of isolation with high concurrency but risks data inconsistency.*/
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<User> readUncommittedUsers() {
        return userRepository.findAll();
    }

    /**
     * <h6>ISOLATION LEVEL READ_COMMITTED</h6>
     * Prevents dirty reads but allows non-repeatable reads (data can change if re-read within the same transaction).
     * A common default for many databases.*/
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> readCommittedUsers() {
        return userRepository.findAll();
    }

    /**
     * <h6>ISOLATION LEVEL REPEATABLE_READ</h6>
     * Prevents dirty reads and non-repeatable reads.
     * Guarantees that if a row is read twice in the same transaction, the data will not change (phantom reads may still occur).*/
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadExample() {
        User user = userRepository.findById(1L).orElseThrow();
        System.out.println("User: " + user);

        // Simulate a long-running transaction
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Re-read the same row
        User sameUser = userRepository.findById(1L).orElseThrow();
        System.out.println("User again: " + sameUser);
        // The data read is consistent within the transaction
    }

    /**
     * <h6>ISOLATION LEVEL SERIALIZABLE</h6>
     * Highest level of isolation, ensuring full consistency by preventing dirty reads, non-repeatable reads, and phantom reads.
     * Transactions are executed sequentially, significantly reducing concurrency.*/
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableExample() {
        userRepository.save(new User("Serialized User", "serialized@example.com"));

        // Simulate another transaction attempting to insert conflicting data
        // No other transaction can interfere until this transaction completes
    }


    @Transactional
    public void createUserWithoutCommit(String name) {
        val user = new User(name, name + "@example.com");
        userRepository.save(user);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Transactional
    public void createUserWithCommit(String name) {
        val user = new User(name, name + "@example.com");
        userRepository.save(user);
        // This method commits automatically since the transaction is short-lived
    }
}

