package com.section_2_changing_default_config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * <h2>Key Differences</h2>
     * <table border="1">
     *   <tr>
     *     <th>Feature</th>
     *     <th>With <code>http.httpBasic()</code></th>
     *     <th>Without <code>http.httpBasic()</code></th>
     *   </tr>
     *   <tr>
     *     <td><strong>Authentication Mechanism</strong></td>
     *     <td>Supports both form-based and HTTP Basic Auth.</td>
     *     <td>Supports only form-based authentication.</td>
     *   </tr>
     *   <tr>
     *     <td><strong>Browser Prompt</strong></td>
     *     <td>Prompts for username/password if unauthorized.</td>
     *     <td>Redirects to login page if unauthorized.</td>
     *   </tr>
     *   <tr>
     *     <td><strong>API Clients</strong></td>
     *     <td>Can authenticate using Basic Auth headers.</td>
     *     <td>Cannot authenticate using Basic Auth headers.</td>
     *   </tr>
     *   <tr>
     *     <td><strong>Stateless Authentication</strong></td>
     *     <td>Yes (for Basic Auth).</td>
     *     <td>No (form-based login relies on sessions).</td>
     *   </tr>
     * </table>
     */


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/notices").denyAll()
        );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
