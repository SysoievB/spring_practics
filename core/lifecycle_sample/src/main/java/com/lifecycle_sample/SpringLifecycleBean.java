package com.lifecycle_sample;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class SpringLifecycleBean implements
        BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String message;

    @Override
    public void setBeanName(String name) {
        System.out.println("--- setBeanName executed from BeanNameAware---");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("--- setApplicationContext executed from ApplicationContextAware---");
    }

    @PostConstruct
    public void postConstructAnnotation() {
        System.out.println("--- @PostConstruct executed ---");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("--- afterPropertiesSet executed from InitializingBean---");
    }

    public void initMethod() {
        System.out.println("--- init-method executed ---");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--- @PreDestroy executed ---");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--- destroy executed from DisposableBean---");
    }

    public void destroyMethod() {
        System.out.println("--- destroy-method executed ---");
    }
}