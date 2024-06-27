package com.configuration_properties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@EnableConfigurationProperties//(ConfigLoggingProperties.class)
@SpringBootApplication
@AllArgsConstructor
public class ConfigurationPropertiesApplication implements CommandLineRunner {
    private final ConfigLoggingProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationPropertiesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("String from properties file -> {}, number -> {}",
                properties.getHello(),
                properties.getNumber());
    }
}
