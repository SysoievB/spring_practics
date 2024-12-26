package com.section_2_disable_form_login_and_httpbasic_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getCardDetails() {
        return "Here are the card details from the DB";
    }
}
