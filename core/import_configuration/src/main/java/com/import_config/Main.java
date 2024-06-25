package com.import_config;

import com.import_config.model.A;
import com.import_config.model.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);
        // now both beans A and B will be available...
        A a = ctx.getBean(A.class);
        B b = ctx.getBean(B.class);
        System.out.println(a);
        System.out.println(b);
    }
}