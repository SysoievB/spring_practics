package com.section_8_csrf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloController {

    /**
     * <h1>CSRF Tokens vs Session Cookies in Spring Security</h1>
     *
     * <p>
     *     In Spring Security, both <strong>Session Cookies</strong> (e.g., JSESSIONID) and
     *     <strong>CSRF Tokens</strong> play crucial roles in ensuring secure web applications,
     *     but they serve different purposes.
     * </p>
     *
     * <h2>Session Cookies (JSESSIONID)</h2>
     * <ul>
     *     <li>
     *         <strong>Purpose:</strong> Session cookies are used to maintain the state of a user's session
     *         across multiple HTTP requests. The server generates a unique session ID (e.g., JSESSIONID)
     *         and sends it to the client as a cookie. The client includes this cookie in subsequent requests
     *         to identify the session.
     *     </li>
     *     <li>
     *         <strong>Usage:</strong> Primarily used for authentication and session management. For example,
     *         after a user logs in, the server creates a session and associates it with the user's session ID.
     *     </li>
     *     <li>
     *         <strong>Security:</strong> While session cookies are essential for maintaining state, they are
     *         vulnerable to attacks like <strong>Session Hijacking</strong> if not properly secured (e.g., using HTTPS).
     *     </li>
     * </ul>
     *
     * <h2>CSRF Tokens</h2>
     * <ul>
     *     <li>
     *         <strong>Purpose:</strong> CSRF (Cross-Site Request Forgery) tokens are used to prevent malicious
     *         websites from making unauthorized requests on behalf of an authenticated user. A unique token is
     *         generated for each request and validated on the server to ensure the request is legitimate.
     *     </li>
     *     <li>
     *         <strong>Usage:</strong> Typically embedded in forms or headers (e.g., as a hidden input field in HTML forms).
     *         The server validates the token with each state-changing request (e.g., POST, PUT, DELETE).
     *     </li>
     *     <li>
     *         <strong>Security:</strong> Protects against CSRF attacks by ensuring that requests originate from
     *         the same application and not from a malicious third-party site.
     *     </li>
     * </ul>
     *
     * <h2>Key Differences</h2>
     * <table border="1">
     *     <tr>
     *         <th>Aspect</th>
     *         <th>Session Cookies (JSESSIONID)</th>
     *         <th>CSRF Tokens</th>
     *     </tr>
     *     <tr>
     *         <td><strong>Purpose</strong></td>
     *         <td>Maintain user session state</td>
     *         <td>Prevent CSRF attacks</td>
     *     </tr>
     *     <tr>
     *         <td><strong>Storage</strong></td>
     *         <td>Stored as a cookie in the browser</td>
     *         <td>Embedded in forms or headers</td>
     *     </tr>
     *     <tr>
     *         <td><strong>Lifetime</strong></td>
     *         <td>Persists for the duration of the session</td>
     *         <td>Generated per request or per session</td>
     *     </tr>
     *     <tr>
     *         <td><strong>Security Risk</strong></td>
     *         <td>Session hijacking if exposed</td>
     *         <td>None if implemented correctly</td>
     *     </tr>
     * </table>
     */


    @GetMapping("/hello")
    String sayHello() {
        return "Hello from rest controller";
    }

    @GetMapping("/hi")
    String sayHi() {
        return "Hi from rest controller";
    }
}
