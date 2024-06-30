package com.one_to_one_with_mapsid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    // cannot be used here @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String street;
    String city;

    @OneToOne(mappedBy = "address")
    User user;

    public Address(String street, String city, User user) {
        this.street = street;
        this.city = city;
        this.user = user;
    }
}
