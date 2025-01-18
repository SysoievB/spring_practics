package com.section_16_resource_server_module;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticlesController {

    @GetMapping("/articles")
    public List<String> getArticles() {
        return List.of("Article 1", "Article 2", "Article 3");
    }
}
