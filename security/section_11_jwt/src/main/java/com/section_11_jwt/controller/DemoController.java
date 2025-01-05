package com.section_11_jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    /**
     *
     * <h2>Steps to Test JWT Authentication</h2>
     *
     * <h3>1. Register a New User</h3>
     * <p><strong>Request:</strong></p>
     * <pre>
     * POST /register HTTP/1.1
     * Content-Type: application/json
     *
     * {
     *   "firstname": "John",
     *   "lastname": "Doe",
     *   "email": "john.doe@example.com",
     *   "password": "password123",
     *   "role": "USER"
     * }
     * </pre>
     * <p><strong>Response:</strong></p>
     * <pre>
     * {
     *   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     * }
     * </pre>
     *
     * <h3>2. Authenticate an Existing User</h3>
     * <p><strong>Request:</strong></p>
     * <pre>
     * POST /authenticate HTTP/1.1
     * Content-Type: application/json
     *
     * {
     *   "email": "john.doe@example.com",
     *   "password": "password123"
     * }
     * </pre>
     * <p><strong>Response:</strong></p>
     * <pre>
     * {
     *   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     * }
     * </pre>
     *
     * <h3>3. Access the Secured <code>/demo</code> Endpoint</h3>
     * <p><strong>Request:</strong></p>
     * <pre>
     * GET /demo HTTP/1.1
     * Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
     * </pre>
     * <p><strong>Response:</strong></p>
     * <pre>
     * {
     *   "message": "This is a secured endpoint!"
     * }
     * </pre>
     *
     * <h2>Why This Works</h2>
     * <p>
     *   The JWT token is issued after successful registration or authentication. The token contains claims (e.g., user roles) that the server uses to authorize access to secured endpoints. By including the token in the <code>Authorization</code> header, the server can verify the user's identity and permissions.
     * </p>
     */
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint!!!");
    }
}
