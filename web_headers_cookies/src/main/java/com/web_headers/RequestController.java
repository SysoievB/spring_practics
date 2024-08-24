package com.web_headers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/http-servlet")
public class RequestController {

    @GetMapping("/request-info")
    public List<String> getRequestInfoMap(HttpServletRequest request) {
        return Map.of(
                        "AuthType", request.getAuthType() != null ? request.getAuthType() : "N/A",
                        "PathInfo", request.getPathInfo() != null ? request.getPathInfo() : "N/A",
                        "Method", request.getMethod(),
                        "RequestURI", request.getRequestURI(),
                        "RequestURL", request.getRequestURL().toString(),
                        "ContextPath", request.getContextPath(),
                        "ServletPath", request.getServletPath(),
                        "QueryString", request.getQueryString() != null ? request.getQueryString() : "N/A"
                )
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .toList();
    }

    @GetMapping("/get-header")
    public String getHeader(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    @GetMapping("/get-all-header-names")
    public List<String> getHeaderNames(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames());
    }

    @GetMapping("/get-all-params")
    public List<String> getParameterMap(HttpServletRequest request) {
        return request.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + " : " + String.join(",", entry.getValue()))
                .toList();
    }

    @GetMapping("/get-all-cookies")
    public List<String> getCookies(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .map(cookie -> cookie.getName() + " : " + cookie.getValue())
                .toList();
    }

    @GetMapping("/get-session")
    public String getSession(HttpServletRequest request) {
        return request.getSession().toString();
    }

    @GetMapping("/get-remote-address")
    public String getRemoteAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping("/get-remote-host")
    public String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    @GetMapping("/get-remote-port")
    public String getRemotePort(HttpServletRequest request) {
        return String.valueOf(request.getRemotePort());
    }

    @GetMapping("/get-content-type")
    public String getContentType(HttpServletRequest request) {
        return Optional.ofNullable(request.getContentType())
                .orElse("N/A");
    }

    @GetMapping("/get-content-length")
    public String getContentLength(HttpServletRequest request) {
        return String.valueOf(request.getContentLength());
    }

    @GetMapping("/get-path-info")
    public String getPathInfo(HttpServletRequest request) {
        return request.getPathInfo();
    }

    @GetMapping("/get-path-translated")
    public String getPathTranslated(HttpServletRequest request) {
        return request.getPathTranslated();
    }
}
