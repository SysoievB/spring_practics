package com.thymeleaf;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

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
    @GetMapping("/layout")//layout.html & content.html
    public String layout(Model model) {
        model.addAttribute("message", "Welcome to layout");
        return "content";
    }

    //Advanced Thymeleaf Features
    @GetMapping("/conditional")
    public String conditional(Model model) {
        val customer = new Customer("John Doe", 12);
        model.addAttribute("customer", customer);
        model.addAttribute("dayOfWeek", LocalDate.now().getDayOfWeek().name());
        return "conditional";
    }

    //Iteration
    @GetMapping("/forEach")
    public String forEach(Model model) {
        val customer1 = new Customer("John Doe", 42);
        val customer2 = new Customer("Ann Doe", 32);
        val customer3 = new Customer("Jim Smith", 22);
        val customers = List.of(customer1, customer2, customer3);
        model.addAttribute("customers", customers);
        return "allCustomers";
    }
}
