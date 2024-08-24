package com.web_headers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/header")
public class HeadersController {

    @GetMapping("/get-x-user-name")
    public String greetUser(@RequestHeader(value = "X-User-Name", defaultValue = "Guest") String username) {
        return "Hello, " + username + "!";
    }

    @GetMapping("/few-headers")
    public String getUserDetails(
            @RequestHeader(value = "X-User-Name", defaultValue = "Guest") String username,
            @RequestHeader(value = "X-User-Role", defaultValue = "User") String role) {
        return "User: " + username + ", Role: " + role;
    }

    @GetMapping("/all-headers")
    public String getAllHeaders(@RequestHeader Map<String, String> headers) {
        return headers.toString();
    }

    @GetMapping("/custom-header")
    public ResponseEntity<String> getCustomHeaderResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Custom-Header", "CustomValue");

        return ResponseEntity.ok()
                .headers(headers)
                .body("Response with custom header");
    }

    @GetMapping("/language")
    public String getUserLanguage(@RequestHeader(value = "Accept-Language", defaultValue = "en") String acceptLanguage) {
        return "Preferred Language: " + acceptLanguage;
    }
}
