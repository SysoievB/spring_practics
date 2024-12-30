package com.section_6_exception_handling;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloController {

    @GetMapping("/hello")
    String sayHello() {
        return "Hello from rest controller";
    }

    @GetMapping("/hi")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String sayHi() {
        return "Hi from rest controller";
    }

    @GetMapping("/hey")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    String sayHey() {
        return "Hey from rest controller";
    }
}
