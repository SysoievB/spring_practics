package com.web_headers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/http-servlet")
public class ResponseController {

    @GetMapping("/response")
    public List<String> example(HttpServletResponse response) {
        // Set status and headers
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Custom-Header", "HeaderValue");
        response.setContentType("text/plain");

        Cookie cookie = new Cookie("sessionId", "abc123");
        response.addCookie(cookie);

        return Map.of(
                "Status", String.valueOf(response.getStatus()),
                "Header value", response.getHeader("Custom-Header"),
                "Content type", String.valueOf(response.getContentType()),
                "Cookie name", cookie.getName(),
                "Cookie value", cookie.getValue()
        )
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .toList();
    }
}
