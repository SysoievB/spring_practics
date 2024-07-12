package com.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class}
)
public class MyServiceConfig {

    @Bean
    @ConditionalOnClass(name = "com.boot.SomeLibraryClass")
    public MyService myServiceWithClass() {
        return new MyService("Service with SomeLibraryClass present");
    }

    @Bean
    @ConditionalOnMissingClass("com.boot.SomeLibraryClass")
    public MyService myServiceWithoutClass() {
        return new MyService("Service without SomeLibraryClass");
    }

    @Bean
    @Conditional(WindowsCondition.class)
    public MyServiceBean windowsService() {
        return new MyServiceBean("Windows Service");
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public MyServiceBean linuxService() {
        return new MyServiceBean("Linux Service");
    }
}