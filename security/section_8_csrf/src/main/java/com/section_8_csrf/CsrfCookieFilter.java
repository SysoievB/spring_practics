package com.section_8_csrf;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    /**
     * <h3>CsrfCookieFilter</h3>
     * <p>
     *     A custom filter that ensures the CSRF token is rendered as a cookie in the response.
     *     This filter is executed after the CSRF token is generated and ensures that the token
     *     is available for the client to use in subsequent requests.
     * </p>
     * <p>
     *     The CSRF token is typically stored in a cookie to allow JavaScript frameworks (e.g., Angular, React)
     *     to access it and include it in requests as a header.
     * </p>
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Retrieve the CSRF token from the request attributes
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Ensure the CSRF token is loaded and rendered as a cookie
        if (csrfToken != null) {
            csrfToken.getToken(); // Forces the token to be loaded and rendered
        }
        filterChain.doFilter(request, response);
    }
}
