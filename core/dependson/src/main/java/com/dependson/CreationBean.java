package com.dependson;

import jakarta.annotation.PostConstruct;

public class CreationBean {

    @PostConstruct
    public void init() {
        System.out.println("CreationBean initialized");
    }
}
