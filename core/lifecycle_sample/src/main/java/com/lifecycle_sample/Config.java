package com.lifecycle_sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    SpringLifecycleBean springLifecycleBean() {
        return new SpringLifecycleBean();
    }

    @Bean
    MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
