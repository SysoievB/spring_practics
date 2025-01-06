package com.section_12_pre_post_filter_annotations;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class DemoController {
    private List<Product> PRODUCTS = List.of(
            new Product("TV-set", 1000.0),
            new Product("Laptop", 1500.0),
            new Product("Phone", 800.0),
            new Product("Tablet", 900.0)
    );

    /**
     * <h1>Comparison of @PreFilter and @PostFilter</h1>
     *
     * <table border="1">
     *   <tr>
     *     <th>Annotation</th>
     *     <th>Purpose</th>
     *     <th>Example Use Case</th>
     *   </tr>
     *   <tr>
     *     <td><code>@PreFilter</code></td>
     *     <td>Filter input collections before method execution.</td>
     *     <td>Remove items from a list that the user is not authorized to access.</td>
     *   </tr>
     *   <tr>
     *     <td><code>@PostFilter</code></td>
     *     <td>Filter output collections after method execution.</td>
     *     <td>Remove items from the returned list that the user is not authorized to see.</td>
     *   </tr>
     * </table>
     *
     * <p>Use <code>@PreFilter</code> to filter collections passed as arguments to a method,
     * and <code>@PostFilter</code> to filter collections returned by a method.</p>
     *
     * <p>Both annotations use SpEL (Spring Expression Language) to define filtering rules.
     * The <code>filterObject</code> keyword refers to each item in the collection being filtered.</p>
     */

    /**
     * <code>@PreFilter</code> works on the input collection passed to the method. Since your preFilter method does
     * not accept any parameters, there is no collection for <code>@PreFilter</code> to filter.*/
    @PostMapping("/pre")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreFilter("filterObject.price > 1000")
    public List<Product> preFilter(@RequestBody List<Product> products) {
        return Optional.ofNullable(products)
                .orElse(Collections.emptyList());
    }

    /**
     * <code>@PostFilter</code> works on the returned collection from the method. In your case, PRODUCTS is returned,
     * and <code>@PostFilter</code> filters it after the method execution.*/
    @GetMapping("/post")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostFilter("filterObject.price < 1000")
    public List<Product> postFilter() {
        return PRODUCTS;
    }
}
