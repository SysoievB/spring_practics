package com.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class LazyApplication implements CommandLineRunner {
    private  ServiceA serviceA;
    private  ServiceB serviceB;
    private  LazyService lazyService;

    @Autowired
    public LazyApplication(ServiceA serviceA, ServiceB serviceB, @Lazy LazyService lazyService) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
        this.lazyService = lazyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LazyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        serviceA.doSomething();
        serviceB.doSomething();
        lazyService.doSomething();
    }
}
/**
 * ServiceA initialized
 * ServiceB initialized
 *
 * ServiceA is doing something
 * ServiceB is doing something
 * LazyService initialized
 * LazyService is doing something*/