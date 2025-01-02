package com.section_8_cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    /**
     * <h3>Explicit saving in the context of Spring Security</h3>
     * <p>
     *     Refers to the behavior of the <code>SecurityContext</code>, which holds the authentication and authorization information for the current user. Specifically, it determines whether the <code>SecurityContext</code> must be explicitly saved to the <code>SecurityContextRepository</code> (e.g., HTTP session) after it is modified.
     * </p>
     *
     * <h4>What is <code>SecurityContext</code>?</h4>
     * <p>
     *     The <code>SecurityContext</code> is an object that stores the details of the currently authenticated user (e.g., their <code>Authentication</code> object). It is typically stored in the <code>SecurityContextHolder</code>, which is a thread-local object that provides access to the <code>SecurityContext</code> for the current thread.
     * </p>
     *
     * <h4>What is Explicit Saving?</h4>
     * <p>
     *     By default, Spring Security automatically saves the <code>SecurityContext</code> to the <code>SecurityContextRepository</code> (e.g., HTTP session) whenever it is modified. However, you can configure Spring Security to require explicit saving of the <code>SecurityContext</code>. This means that the <code>SecurityContext</code> will <strong>not</strong> be automatically saved after modifications unless you explicitly call the <code>save</code> method.
     * </p>
     *
     * <h4>Why Use Explicit Saving?</h4>
     * <p>
     *     Explicit saving is useful in scenarios where you want to optimize performance or control when the <code>SecurityContext</code> is persisted. For example:
     * </p>
     * <ul>
     *     <li>
     *         <strong>Performance Optimization:</strong>
     *         <ul>
     *             <li>If the <code>SecurityContext</code> is modified frequently, automatically saving it after every modification can lead to unnecessary overhead (e.g., frequent writes to the session).</li>
     *             <li>By requiring explicit saving, you can control when the <code>SecurityContext</code> is persisted, reducing unnecessary operations.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <strong>Fine-Grained Control:</strong>
     *         <ul>
     *             <li>You may want to save the <code>SecurityContext</code> only after specific operations or at the end of a request, rather than after every modification.</li>
     *         </ul>
     *     </li>
     * </ul>
     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure SecurityContext to not require explicit saving
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))

                // Configure session management to always create a session
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

                // Configure CORS (Cross-Origin Resource Sharing)
                .cors(corsConfig -> corsConfig.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    // Allow requests from this origin
                    config.setAllowedOrigins(Collections.singletonList("https://localhost:4200"));
                    // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
                    config.setAllowedMethods(Collections.singletonList("*"));
                    // Allow credentials (e.g., cookies) to be sent with the request
                    config.setAllowCredentials(true);
                    // Allow all headers in the request
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    // Set the maximum age (in seconds) for which the CORS preflight response is cached
                    config.setMaxAge(3600L);
                    return config;
                }))
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/hi").authenticated()
                                .requestMatchers("/hello").permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
