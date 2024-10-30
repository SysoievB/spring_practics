package com.aop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car implements Vehiclable {
    private Integer id;
    private String name;
    private int year;
    private boolean auto;

    public Car(Integer id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}