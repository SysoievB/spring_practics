package com.spring_profiles;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class ProfilesController {
    private final Profilelable profilelable;

    @GetMapping
    public String hello() {
        return profilelable.hello();
    }
}
