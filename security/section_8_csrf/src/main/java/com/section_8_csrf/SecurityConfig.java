package com.section_8_csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {

    /**
     * <h3>Cross-Site Request Forgery (CSRF) Protection</h3>
     * <p>
     *     CSRF is an attack where a malicious site tricks a user into performing unwanted actions on a trusted site where they are authenticated.
     *     Spring Security provides robust CSRF protection using the <strong>Synchronizer Token Pattern</strong>:
     * </p>
     * <ul>
     *     <li>A unique CSRF token is generated for each session and included in forms or headers.</li>
     *     <li>The server validates the token on state-changing requests (e.g., POST, PUT, DELETE).</li>
     *     <li>Tokens are stored in cookies (accessible to JavaScript) or session attributes.</li>
     * </ul>
     * <p>
     *     <strong>Key Considerations:</strong>
     * </p>
     * <ul>
     *     <li>Safe methods (GET, HEAD, OPTIONS, TRACE) should be read-only and not change application state.</li>
     *     <li>CSRF protection can be disabled for public endpoints using <code>ignoringRequestMatchers</code>.</li>
     *     <li>The <code>SameSite</code> cookie attribute can provide additional defense by restricting cookie usage to same-site requests.</li>
     * </ul>
     * <p>
     *     <strong>Example:</strong>
     *     Use <code>CookieCsrfTokenRepository.withHttpOnlyFalse()</code> to store the CSRF token in a cookie accessible to JavaScript frameworks.
     * </p>
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http // Configure CSRF protection
                .csrf(csrfConfig -> csrfConfig
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()) // Use the default CSRF token request handler
                        .ignoringRequestMatchers("/hello") // Disable CSRF protection for the /hello endpoint (public API)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Store the CSRF token in a cookie with HttpOnly set to false
                )
                // Add the CsrfCookieFilter after the BasicAuthenticationFilter
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi").authenticated()
                                .requestMatchers("/hello").permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}54321")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
