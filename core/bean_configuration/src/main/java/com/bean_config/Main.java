package com.bean_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
    }
}