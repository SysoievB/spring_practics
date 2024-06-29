package com.lifecycle_sample;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringLifecycleBean) {
            System.out.println("--- postProcessBeforeInitialization executed ---");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringLifecycleBean) {
            System.out.println("--- postProcessAfterInitialization executed ---");
        }
        return bean;
    }
}

