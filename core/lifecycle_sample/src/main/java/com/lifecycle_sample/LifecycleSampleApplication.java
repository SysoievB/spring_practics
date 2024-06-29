package com.lifecycle_sample;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class LifecycleSampleApplication implements CommandLineRunner {
    private final SpringLifecycleBean springLifecycleBean;

    public static void main(String[] args) {
        SpringApplication.run(LifecycleSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        springLifecycleBean.setMessage("Hello !!!!!");
        System.out.println(springLifecycleBean.getMessage());
    }
}
/**
 * --- setBeanName executed from BeanNameAware---
 * --- setApplicationContext executed from ApplicationContextAware---
 * --- postProcessBeforeInitialization executed ---
 * --- @PostConstruct executed ---
 * --- afterPropertiesSet executed from InitializingBean---
 * --- init-method executed ---
 * --- postProcessAfterInitialization executed ---
 * Hello !!!!!
 * --- @PreDestroy executed ---
 * --- destroy executed from DisposableBean---
 * --- destroy-method executed ---
 * */