package com.section_15_keycloack_openid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OidcUser user) {
        return String.format("Welcome, %s !", user.getFullName());
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }
}