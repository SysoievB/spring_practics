package com.section_6_exception_handling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi", "/hey").authenticated()
                                .requestMatchers("/hello").permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new CustomAccessDeniedHandler()));
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
