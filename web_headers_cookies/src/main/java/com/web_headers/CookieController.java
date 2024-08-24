package com.web_headers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CookieController {

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response, @RequestParam String name, @RequestParam String value) {
        val cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);//1 hour
        response.addCookie(cookie);
        return "Cookie set: " + name + "=" + value;
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(value = "myCookie", defaultValue = "default") String myCookie) {
        return "Cookie value: " + myCookie;
    }

    @GetMapping("/delete-cookie")
    @ResponseBody
    public String deleteCookie(HttpServletResponse response) {
        val cookie = new Cookie("myCookie", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "Cookie deleted";
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue())
                    .collect(Collectors.joining(", "));
        }

        return "No cookies";
    }
}