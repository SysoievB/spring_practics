package com.section_10_custom_filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class CustomFilterAt implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("-------------CustomFilterAt started execution of doFilter at {}-----------", LocalDateTime.now());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("-------------CustomFilterAt finished execution of doFilter at {}-----------", LocalDateTime.now());
    }
}
