package com.optimistic_lock_exception;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product createProduct(String name, Double price) {
        Product product = new Product(null, name, price, null);
        return repository.save(product);
    }

    @Transactional
    public Product updateProductPrice(Long productId, Double newPrice) {
        return repository.findById(productId)
                .map(prod -> {
                    prod.setPrice(newPrice);
                    return repository.save(prod);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product findProductById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}

