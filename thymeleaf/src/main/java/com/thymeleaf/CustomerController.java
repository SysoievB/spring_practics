package com.thymeleaf;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/")//index.html
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf!");
        return "index";
    }

    @GetMapping("/customer")//customer.html
    public String customer(Model model) {
        val customer = new Customer("John Doe", 30);
        model.addAttribute("customer", customer);
        return "customer";
    }
}
