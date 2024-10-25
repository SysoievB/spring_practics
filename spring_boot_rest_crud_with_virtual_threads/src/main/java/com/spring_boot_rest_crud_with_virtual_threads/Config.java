package com.spring_boot_rest_crud_with_virtual_threads;

import org.springframework.boot.autoconfigure.condition.ConditionalOnThreading;
import org.springframework.boot.autoconfigure.thread.Threading;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class Config {

    @Bean
    @ConditionalOnThreading(Threading.PLATFORM)
    public ExecutorService platformExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    @ConditionalOnThreading(Threading.VIRTUAL)
    public ExecutorService virtualExecutorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
