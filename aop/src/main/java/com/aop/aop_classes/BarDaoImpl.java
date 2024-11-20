package com.aop.aop_classes;

import org.springframework.stereotype.Component;

@Component
public class BarDaoImpl implements BarDao {
    @Override
    public String bar() {
        return "Hello from bar";
    }
}
