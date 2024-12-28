package com.section_4_managing_users_with_db.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "customer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String password;
    //@Column(name = "role")
    String role;

    public Customer(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
