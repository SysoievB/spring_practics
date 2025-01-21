package com.section_3_in_memory_user_details_manager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void successfullyLoginWithUser() {
        ResponseEntity<String> result = template.withBasicAuth("user", "BsSecurity@12345")
                .getForEntity("/myAccount", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void successfullyLoginWithAdmin() {
        ResponseEntity<String> result = template.withBasicAuth("admin", "{bcrypt}$2a$12$lIBxO71DGD4eCZyaWcbNouz2HRpcGVlgltsHN19QWbkS6vR9F0VrG")
                .getForEntity("/myAccount", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void failedLoginWithInvalidCredentials() {
        ResponseEntity<String> result = template.withBasicAuth("invalidUser", "invalidPassword")
                .getForEntity("/myAccount", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    void accessPublicEndpointWithoutAuthentication() {
        ResponseEntity<String> result = template.getForEntity("/notices", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void accessSecuredEndpointWithoutAuthentication() {
        ResponseEntity<String> result = template.getForEntity("/myAccount", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }
}