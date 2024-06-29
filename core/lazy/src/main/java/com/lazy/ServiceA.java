package com.lazy;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {

    public ServiceA() {
        System.out.println("ServiceA initialized");
    }

    public void doSomething() {
        System.out.println("ServiceA is doing something");
    }
}
