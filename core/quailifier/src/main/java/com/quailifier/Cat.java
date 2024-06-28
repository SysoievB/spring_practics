package com.quailifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cat implements Animal{
    String name;
    @Override
    public void eat() {
        System.out.println(name + " eats");
    }
}
