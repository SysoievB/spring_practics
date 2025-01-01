package com.thymeleaf;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    //Create a Simple HTML Template
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

    //Forms and Validation
    @GetMapping("/form")//form.html
    public String form(Model model) {
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @PostMapping("/submitForm")//form.html
    public String submitForm(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer";
    }

    //Layouts and Fragments
    @GetMapping("/layout")//layout.html
    public String layout() {
        return "layout";
    }
}
