package com.spring_transactions;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Entity
@Table(name = "users")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
