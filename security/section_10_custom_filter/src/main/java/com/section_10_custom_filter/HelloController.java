package com.section_10_custom_filter;

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
    String sayHi() {
        return "Hi from rest controller";
    }
}
