package com.dependson;

import jakarta.annotation.PostConstruct;

public class ProcessingBean {

    @PostConstruct
    public void init() {
        System.out.println("ProcessingBean initialized");
    }
}
