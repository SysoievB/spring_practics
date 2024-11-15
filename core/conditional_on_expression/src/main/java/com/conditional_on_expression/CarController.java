package com.conditional_on_expression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@ConditionalOnExpression("${controller.enabled:true}")
public class CarController {

    @GetMapping
    Car getCar() {
        return new Car("BMW", 2020);
    }

    record Car(String name, int age) {
    }
}
