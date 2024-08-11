package com.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class User {
    Long id;
    String username;
    String password;
    String fullName;
    LocalDate lastLogin;

    public User setPassword(String encrypted) {
        this.password = encrypted;
        return this;
    }
}