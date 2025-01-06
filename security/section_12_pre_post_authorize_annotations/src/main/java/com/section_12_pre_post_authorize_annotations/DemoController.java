package com.section_12_pre_post_authorize_annotations;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DemoController {

    /**
     * <h1>Comparison of @PreAuthorize and @PostAuthorize</h1>
     *
     * <table border="1">
     *   <tr>
     *     <th>Annotation</th>
     *     <th>Timing</th>
     *     <th>Purpose</th>
     *     <th>Example Use Case</th>
     *   </tr>
     *   <tr>
     *     <td><code>@PreAuthorize</code></td>
     *     <td>Before</td>
     *     <td>Restrict access to the method.</td>
     *     <td>Role-based access control.</td>
     *   </tr>
     *   <tr>
     *     <td><code>@PostAuthorize</code></td>
     *     <td>After</td>
     *     <td>Filter or restrict returned data.</td>
     *     <td>Ensure user owns the returned data.</td>
     *   </tr>
     * </table>
     *
     * <p>Use <code>@PreAuthorize</code> to enforce security rules before a method is executed,
     * and <code>@PostAuthorize</code> to enforce rules after the method returns a value.</p>
     */

    @GetMapping("/hello")
    String sayHello() {
        return "Hello from rest controller";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "This is a user endpoint!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "This is an admin endpoint!";
    }

    @GetMapping("/data")
    @PostAuthorize("hasRole('USER')")
    public Data getData() {
        return new Data("Sample Data", "user");
    }
}
