package com.webflux.functional_web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    private Long id;

    private String name;

    private Integer age;

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
