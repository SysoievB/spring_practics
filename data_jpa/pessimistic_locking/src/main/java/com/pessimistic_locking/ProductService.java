package com.pessimistic_locking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(String name, Double price) {
        return productRepository.save(new Product(name, price));
    }

    // PESSIMISTIC_READ
    @Transactional
    public Product findProductForReading(String name) {
        return productRepository.findByName(name);
    }

    // PESSIMISTIC_WRITE
    @Transactional
    public Product updateProduct(Long id, double newPrice) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setPrice(newPrice);
                    return product;
                })
                .map(productRepository::save)
                .orElseThrow(RuntimeException::new);
    }
}
