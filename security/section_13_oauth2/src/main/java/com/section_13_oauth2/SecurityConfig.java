package com.section_13_oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * <h1>Spring Security OAuth2 Overview</h1>
     *
     * <p>
     *     Spring Security OAuth2 is a framework that enables secure authentication and authorization
     *     in applications using the OAuth2 protocol. OAuth2 is an industry-standard protocol for
     *     authorization, allowing third-party applications to access user resources without exposing
     *     user credentials.
     * </p>
     *
     * <h2>Key Concepts</h2>
     *
     * <table border="1">
     *   <tr>
     *     <th>Concept</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td><strong>OAuth2</strong></td>
     *     <td>
     *       An authorization framework that allows third-party applications to obtain limited access
     *       to a user's resources on a server without sharing the user's credentials.
     *     </td>
     *   </tr>
     *   <tr>
     *     <td><strong>Roles</strong></td>
     *     <td>
     *       <ul>
     *         <li><strong>Resource Owner</strong>: The user who owns the data (e.g., a person).</li>
     *         <li><strong>Client</strong>: The application requesting access to the user's data.</li>
     *         <li><strong>Resource Server</strong>: The server hosting the protected resources.</li>
     *         <li><strong>Authorization Server</strong>: The server that issues access tokens after authenticating the user.</li>
     *       </ul>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td><strong>Grant Types</strong></td>
     *     <td>
     *       <ul>
     *         <li><strong>Authorization Code</strong>: Used by web applications to obtain an access token.</li>
     *         <li><strong>Implicit</strong>: Simplified flow for browser-based or mobile apps.</li>
     *         <li><strong>Password</strong>: Used by trusted applications to exchange user credentials for a token.</li>
     *         <li><strong>Client Credentials</strong>: Used for server-to-server authentication.</li>
     *         <li><strong>Refresh Token</strong>: Used to obtain a new access token when the current one expires.</li>
     *       </ul>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td><strong>Tokens</strong></td>
     *     <td>
     *       <ul>
     *         <li><strong>Access Token</strong>: A short-lived token used to access protected resources.</li>
     *         <li><strong>Refresh Token</strong>: A long-lived token used to obtain a new access token.</li>
     *       </ul>
     *     </td>
     *   </tr>
     * </table>
     */
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/secured").authenticated()
                );
        http.oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
