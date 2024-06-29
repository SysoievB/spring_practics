package com.lazy;

import org.springframework.stereotype.Component;

@Component
public class ServiceB {


    public ServiceB() {
        System.out.println("ServiceB initialized");
    }

    public void doSomething() {
        System.out.println("ServiceB is doing something");
    }
}
