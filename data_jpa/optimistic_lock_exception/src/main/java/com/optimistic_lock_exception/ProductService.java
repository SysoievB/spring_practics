package com.optimistic_lock_exception;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product createProduct(String name, Double price) {
        return repository.save(new Product(name, price));
    }

    /**
     * on front-end we can throw custom exception and refresh the page
     */
    @Transactional
    public void updateProductPrice(Long productId, Double newPrice) {
        repository.findById(productId)
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

