package com.dependson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ComponentScan
public class Config {

    @Bean
    @DependsOn({"creationBean"})
    ProcessingBean processingBean() {
        return new ProcessingBean();
    }

    @Bean
    CreationBean creationBean() {
        return new CreationBean();
    }
}
