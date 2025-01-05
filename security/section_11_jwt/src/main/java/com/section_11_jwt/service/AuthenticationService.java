package com.section_11_jwt.service;

import com.section_11_jwt.controller.AuthenticationRequest;
import com.section_11_jwt.controller.AuthenticationResponse;
import com.section_11_jwt.controller.RegisterRequest;
import com.section_11_jwt.model.User;
import com.section_11_jwt.model.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        val user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        repository.save(user);
        val jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        val user = repository.findByEmail(request.email())
                .orElseThrow();
        val jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
