package com.section_7_thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mainPage")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/errorPage")
    public String errorPage() {
        return "errorPage";
    }
}