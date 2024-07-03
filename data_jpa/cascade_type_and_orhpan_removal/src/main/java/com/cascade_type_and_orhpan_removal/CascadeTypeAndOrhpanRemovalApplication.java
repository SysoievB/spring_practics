package com.cascade_type_and_orhpan_removal;

import com.cascade_type_and_orhpan_removal.entity.Article;
import com.cascade_type_and_orhpan_removal.entity.Comment;
import com.cascade_type_and_orhpan_removal.entity.PrimeComment;
import com.cascade_type_and_orhpan_removal.repo.ArticleRepo;
import com.cascade_type_and_orhpan_removal.repo.CommentRepo;
import com.cascade_type_and_orhpan_removal.repo.PrimeCommentRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CascadeTypeAndOrhpanRemovalApplication implements CommandLineRunner {
    private final ArticleRepo articleRepo;
    private final CommentRepo commentRepo;
    private final PrimeCommentRepo primeCommentRepo;

    public static void main(String[] args) {
        SpringApplication.run(CascadeTypeAndOrhpanRemovalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val comments = List.of(
                new Comment("Some comment"),
                new Comment("Some another comment")
        );

        val primeComments = List.of(
                new PrimeComment("Some prime comment"),
                new PrimeComment("Some another prime comment")
        );

        val article = new Article("Some article");
        article.setComments(comments);
        article.setPrimeComments(primeComments);
        articleRepo.save(article);

        val articleFromDb = articleRepo.findById(1L).get();

        //cascade = REMOVE
        articleFromDb.setComments(Collections.emptyList());
        System.out.println(articleRepo.save(articleFromDb));
        System.out.println(commentRepo.findById(1L).orElse(null));
        System.out.println(commentRepo.findById(2L).orElse(null));
        //Article(id=1, name=Some article, comments=[], primeComments=[])
        //Comment(id=1, description=Some comment)
        //Comment(id=2, description=Some another comment)

        //orphanRemoval = true
        articleFromDb.setPrimeComments(Collections.emptyList());
        System.out.println(articleRepo.save(articleFromDb));
        System.out.println(primeCommentRepo.findById(1L).orElse(null));
        System.out.println(primeCommentRepo.findById(2L).orElse(null));
        //Article(id=1, name=Some article, comments=[], primeComments=[])
        //null
        //null
    }
}
