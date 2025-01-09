package com.section_14_openid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User user, Model model) {
        String username = user.getAttribute("login");
        model.addAttribute("username", username);
        return "home-page";
    }

    @GetMapping("/hello")
    public String hello() {
        return "welcome-page";
    }
}