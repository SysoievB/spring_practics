package com.spring_boot_rest_crud_with_virtual_threads;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
    String model;
    String engine;
    int year;
}
