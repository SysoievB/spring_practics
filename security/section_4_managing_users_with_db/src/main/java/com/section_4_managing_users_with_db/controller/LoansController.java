package com.section_4_managing_users_with_db.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public String getLoanDetails() {
        return "Here are the loan details from the DB";
    }
}
