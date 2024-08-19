package com.pessimistic_locking;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PESSIMISTIC_READ: Allows multiple reads but prevents updates and deletes.
 * PESSIMISTIC_WRITE: Prevents reads, updates, and deletes.
 * PESSIMISTIC_FORCE_INCREMENT: Same as PESSIMISTIC_WRITE but increments a version attribute.
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Product findByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long id);
}
