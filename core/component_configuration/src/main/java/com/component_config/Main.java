package com.component_config;

import com.component_config.config.CarConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        CarProvider carProvider = applicationContext.getBean(CarProvider.class);
        System.out.println(carProvider.getCar());
    }
}