package com.pagination;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "address")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    Long id;

    String street;
    String city;
    int houseNumber;
    int flatNumber;

    public Address(String street, String city, int houseNumber, int flatNumber) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
