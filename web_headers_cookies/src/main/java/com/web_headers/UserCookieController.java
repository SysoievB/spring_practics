package com.web_headers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserCookieController {
    @PostMapping("/register")
    public String registerUser(@RequestBody User user, HttpServletResponse response) {
        Cookie cookie = new Cookie("usernameCookie", user.getName());
        response.addCookie(cookie);
        return "User registered successfully!";
    }

    @GetMapping("/welcome")
    public String welcomeUser(@CookieValue(value = "usernameCookie", defaultValue = "Guest") String username) {
        return "Welcome, " + username + "!";
    }
}
