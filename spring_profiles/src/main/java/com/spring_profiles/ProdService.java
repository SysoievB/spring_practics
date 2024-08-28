package com.spring_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdService implements Profilelable {
    @Override
    public String hello() {
        return "Hello from prod";
    }
}
