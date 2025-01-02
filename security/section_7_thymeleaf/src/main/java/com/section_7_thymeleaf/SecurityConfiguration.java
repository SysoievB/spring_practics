package com.section_7_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/mainPage").authenticated()
                        .requestMatchers("/login", "/errorPage").permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/mainPage") // Redirect after successful login
                        .failureUrl("/errorPage") // Redirect after failed login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout URL
                        .logoutSuccessUrl("/login?logout") // Redirect after logout
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

    /**
     * <h3>@Autowired on a Method</h3>
     * <p><strong>Method Parameter Injection:</strong></p>
     * <ul>
     *     <li>Spring automatically resolves the parameters of the method and injects the appropriate beans.</li>
     *     <li>The method can have any number of parameters, and Spring will inject the beans that match the parameter types.</li>
     * </ul>
     *
     * <p><strong>Invocation Timing:</strong></p>
     * <ul>
     *     <li>The method is called <em>after the bean is created</em> and its dependencies are injected.</li>
     *     <li>This makes it a good place for initialization logic.</li>
     * </ul>
     *
     * <p><strong>Usage:</strong></p>
     * <ul>
     *     <li>Commonly used in <strong>setter methods</strong> or <strong>configuration methods</strong>.</li>
     * </ul>
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder passwordEncoder) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("12345")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("54321")).roles("ADMIN");
    }
}
