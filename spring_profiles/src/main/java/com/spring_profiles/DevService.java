package com.spring_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevService implements Profilelable {
    @Override
    public String hello() {
        return "Hello from dev";
    }
}
