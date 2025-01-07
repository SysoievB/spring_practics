package com.section_13_oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from controller";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello from secured controller!!!";
    }
}
