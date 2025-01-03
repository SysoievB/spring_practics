package com.section_9_authorities_roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi").hasAuthority("view_hi")
                                .requestMatchers("/hey").hasAuthority("view_hey")
                                .requestMatchers("/userAndAdmin").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/adminOnly").hasRole("ADMIN")
                                .requestMatchers("/hello").permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}12345")
                .authorities("view_hi", "ROLE_USER")
                //.roles("USER") needs to be OR .authorities() OR .roles()
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}54321")
                .authorities("view_hi", "view_hey", "ROLE_ADMIN")
                //.roles("ADMIN") needs to be OR .authorities() OR .roles()
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
