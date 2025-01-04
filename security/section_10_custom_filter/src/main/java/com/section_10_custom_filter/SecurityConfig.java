package com.section_10_custom_filter;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new CustomBeforeFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new CustomAfterFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new CustomFilterAt(), LogoutFilter.class)
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/hey").hasRole("ADMIN")
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
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}54321")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
