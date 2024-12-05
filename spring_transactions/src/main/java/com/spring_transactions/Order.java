package com.spring_transactions;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Entity
@Table(name = "orders")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String product;
    Double amount;

    public Order(String product, Double amount) {
        this.product = product;
        this.amount = amount;
    }
}

