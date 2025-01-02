package com.section_8_cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloController {

    @GetMapping("/hello")
    String sayHello() {
        return "Hello from rest controller";
    }

    /**
     * <h3>Maps HTTP GET requests to the /hi endpoint.</h3>
     *
     * <h5>@CrossOrigin(origins = "http://localhost:3000"):</h5>
     * <ul>
     *   <li>Enables CORS for this specific endpoint.</li>
     *   <li>The "Access-Control-Allow-Origin" header will be set to "http://localhost:3000".</li>
     *   <li>This allows requests from "http://localhost:3000" to access this endpoint.</li>
     * </ul>
     *
     * <h5>How the browser handles it:</h5>
     * <ol>
     *   <li>The browser sends a request to the server.</li>
     *   <li>The server responds with the "Access-Control-Allow-Origin" header.</li>
     *   <li>The browser checks if the origin of the request matches the value of the header.</li>
     *   <li>If it matches, the browser allows the response to be accessed by the client.</li>
     *   <li>If it doesn't match, the browser blocks the response and throws a CORS error.</li>
     * </ol>
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hi")
    String sayHi() {
        return "Hi from rest controller";
    }
}
