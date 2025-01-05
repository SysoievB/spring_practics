package com.section_11_jwt.controller;

import com.section_11_jwt.model.Role;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password,
        Role role) {
}
