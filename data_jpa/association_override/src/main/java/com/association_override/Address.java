package com.association_override;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    String street;
    String postalCode;

    @ManyToOne
    @JoinColumn(name = "name")
    City city;
}
