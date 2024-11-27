package com.test_configuration;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public String findUserById(int id) {
        return "User" + id;
    }
}