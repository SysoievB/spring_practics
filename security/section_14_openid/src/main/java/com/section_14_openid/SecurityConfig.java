package com.section_14_openid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * <h1>OpenID vs OAuth2</h1>
     * <h2>Purpose</h2>
     * <p>
     * <strong>OAuth 2.0:</strong>
     * <ul>
     *         <li>Primarily designed for <strong>authorization</strong>.</li>
     *         <li>Allows third-party applications to access resources on behalf of a user without sharing the user's credentials.</li>
     *         <li><strong>Example:</strong> A third-party app accessing your Google Drive files.</li>
     *     </ul>
     * </p>
     * <p>
     *     <strong>OpenID Connect (OIDC):</strong>
     *     <ul>
     *         <li>Built on top of OAuth 2.0 and designed for <strong>authentication</strong>.</li>
     *         <li>Provides a way to verify the identity of a user (single sign-on or SSO).</li>
     *         <li><strong>Example:</strong> Logging into a website using your Google account.</li>
     *     </ul>
     * </p>
     * <h2>Tokens</h2>
     * <p>
     *     <strong>OAuth 2.0:</strong>
     *     <ul>
     *         <li><strong>Access Token:</strong> A token used to access protected resources.</li>
     *         <li><strong>Refresh Token:</strong> A token used to obtain a new access token when the current one expires.</li>
     *     </ul>
     * </p>
     * <p>
     *     <strong>OpenID Connect (OIDC):</strong>
     *     <ul>
     *         <li><strong>ID Token:</strong> A JSON Web Token (JWT) that contains information about the user's identity (e.g., name, email, etc.).</li>
     *         <li><strong>Access Token:</strong> Same as in OAuth 2.0, used to access resources.</li>
     *         <li><strong>Refresh Token:</strong> Same as in OAuth 2.0.</li>
     *     </ul>
     * </p>
     * <h2>Example Scenarios</h2>
     * <p>
     *     <strong>OAuth 2.0:</strong>
     *     <ul>
     *         <li>A mobile app requests access to your Google Calendar to add events.</li>
     *         <li>A third-party app requests permission to post tweets on your behalf.</li>
     *     </ul>
     * </p>
     * <p>
     *     <strong>OpenID Connect (OIDC):</strong>
     *     <ul>
     *         <li>Logging into a website using your Google or Facebook account.</li>
     *         <li>Single Sign-On (SSO) across multiple enterprise applications.</li>
     *     </ul>
     * </p>
     * */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                        .failureUrl("/login?error=true") // Redirect after login failure
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout-success") // Redirect after logout
                );
        return http.build();
    }
}
