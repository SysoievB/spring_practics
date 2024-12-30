package com.section_6_session_timeout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(securitySessionManagementConfigurer -> securitySessionManagementConfigurer
                        // Redirect users to this URL when an invalid session is detected.
                        // An invalid session occurs when the session ID in the client's request does not match any active session on the server.
                        // This is useful for handling expired or tampered sessions.
                        .invalidSessionUrl("/invalidSession")

                        // Set the maximum number of sessions allowed for a single user.
                        // In this case, a user can only have 1 active session at a time.
                        // If the user tries to log in from another device or browser, the existing session will be invalidated.
                        .maximumSessions(1)

                        // When set to `true`, prevents a user from logging in if they already have the maximum number of active sessions.
                        // In this case, if a user is already logged in with 1 active session, they will not be able to log in again until the existing session is terminated.
                        // If set to `false`, the oldest session for the user would be invalidated, and the new login would succeed.
                        .maxSessionsPreventsLogin(true)
                )
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
                .csrf(AbstractHttpConfigurer::disable) // By default, enabled
                // Disables Cross-Site Request Forgery (CSRF) protection for the application.
                // CSRF is a security feature that prevents unauthorized commands from being executed on behalf of an authenticated user.
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi").authenticated()
                                .requestMatchers("/hello", "/invalidSession").permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}54321")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
