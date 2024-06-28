package com.primary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog implements Animal {
    String name;
    @Override
    public void eat() {
        System.out.println(name + " eats");
    }
}