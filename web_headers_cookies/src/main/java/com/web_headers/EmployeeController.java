package com.web_headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/demo")
    public void handle(@RequestHeader("Accept-Encoding") String encoding,
                       @RequestHeader("Keep-Alive") long keepAlive) {

    }
}
