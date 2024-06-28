package com.lookup;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class LookupApplication implements CommandLineRunner {
    private final SingletonBean singletonBean;

    public static void main(String[] args) {
        SpringApplication.run(LookupApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        singletonBean.getNotificationFromPrototype();
    }
}
