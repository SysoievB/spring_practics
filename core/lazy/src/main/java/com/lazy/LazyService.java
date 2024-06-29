package com.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LazyService {
    
    public LazyService() {
        System.out.println("LazyService initialized");
    }

    public void doSomething() {
        System.out.println("LazyService is doing something");
    }
}
