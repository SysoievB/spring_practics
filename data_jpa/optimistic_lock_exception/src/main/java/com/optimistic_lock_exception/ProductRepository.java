package com.optimistic_lock_exception;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @Lock(LockModeType.OPTIMISTIC)
    Optional<Product> findById(Long id);
}

