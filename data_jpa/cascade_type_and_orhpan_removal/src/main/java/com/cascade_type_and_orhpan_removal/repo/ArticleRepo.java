package com.cascade_type_and_orhpan_removal.repo;

import com.cascade_type_and_orhpan_removal.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
