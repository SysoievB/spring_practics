package com.section_9_authorities_roles;

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

    @GetMapping("/hey")
    String sayHey() {
        return "Hey from rest controller";
    }

    @GetMapping("/userAndAdmin")
    String userAndAdmin() {
        return "User & admin only from rest controller";
    }

    @GetMapping("/adminOnly")
    String adminOnly() {
        return "Admin only from rest controller";
    }
}
