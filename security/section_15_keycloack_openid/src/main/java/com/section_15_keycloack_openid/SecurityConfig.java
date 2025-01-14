package com.section_15_keycloack_openid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * <h1>1. Realm (Сфера)</h1>
     * <h2>What is a Realm?</h2>
     * <p>
     * A Realm in Keycloak is a space where you manage:
     * </p>
     * <ul>
     *   <li><strong>Users:</strong> Individuals who can authenticate.</li>
     *   <li><strong>Clients:</strong> Applications that can request authentication and authorization.</li>
     *   <li><strong>Roles:</strong> Permissions or groups that define what users can do.</li>
     *   <li><strong>Identity Providers:</strong> External systems (like Google, Facebook, etc.) that can be used for authentication.</li>
     * </ul>
     * <p>
     * A realm acts as a security boundary. Each realm is isolated, meaning users, clients, and roles in one realm are not accessible in another.
     * </p>
     *
     * <h2>Why Use Realms?</h2>
     * <ul>
     *   <li><strong>Isolation:</strong> Separate environments for different applications or organizations.</li>
     *   <li><strong>Customization:</strong> Each realm can have its own authentication flows, themes, and policies.</li>
     *   <li><strong>Multi-tenancy:</strong> Support for multiple organizations or applications within a single Keycloak instance.</li>
     * </ul>
     *
     * <h2>Steps to Create a Realm</h2>
     * <ol>
     *   <li><strong>Log in to Keycloak Admin Console:</strong>
     *     <ul>
     *       <li>Access <a href="http://localhost:8080">http://localhost:8080</a> and log in with the admin username and password.</li>
     *     </ul>
     *   </li>
     *   <li><strong>Create a New Realm:</strong>
     *     <ul>
     *       <li>Click the dropdown in the top-left corner and select <strong>Add Realm</strong>.</li>
     *       <li>Enter a <strong>Realm Name</strong> (e.g., myrealm).</li>
     *       <li>Click <strong>Create</strong>.</li>
     *     </ul>
     *   </li>
     *   <li><strong>Configure the Realm:</strong>
     *     <ul>
     *       <li>You can customize settings like:
     *         <ul>
     *           <li><strong>Themes:</strong> Change the look and feel of login pages.</li>
     *           <li><strong>Tokens:</strong> Configure token expiration times.</li>
     *           <li><strong>Authentication:</strong> Set up multi-factor authentication or password policies.</li>
     *         </ul>
     *       </li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <h1>2. Client</h1>
     * <h2>What is a Client?</h2>
     * <p>
     * A Client in Keycloak represents an application that can request authentication and authorization. Examples of clients include:
     * </p>
     * <ul>
     *   <li>Web applications</li>
     *   <li>Mobile apps</li>
     *   <li>APIs</li>
     * </ul>
     * <p>
     * Each client is associated with a realm and has its own configuration, such as:
     * </p>
     * <ul>
     *   <li><strong>Client ID:</strong> A unique identifier for the client.</li>
     *   <li><strong>Client Secret:</strong> A password used to authenticate the client with Keycloak.</li>
     *   <li><strong>Redirect URIs:</strong> URLs where users are redirected after authentication.</li>
     *   <li><strong>Access Type:</strong> Defines how the client interacts with Keycloak (e.g., confidential, public, bearer-only).</li>
     * </ul>
     *
     * <h2>Why Use Clients?</h2>
     * <ul>
     *   <li><strong>Authentication:</strong> Allow users to log in to your application.</li>
     *   <li><strong>Authorization:</strong> Control what users can do in your application.</li>
     *   <li><strong>Integration:</strong> Connect your application to Keycloak for centralized identity management.</li>
     * </ul>
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwkSetUri("http://localhost:8080/realms/section_15_keycloack_openid/protocol/openid-connect/certs"))
                );
        return http.build();
    }
}