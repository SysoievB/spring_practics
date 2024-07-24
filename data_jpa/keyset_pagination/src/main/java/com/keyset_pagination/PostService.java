package com.keyset_pagination;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.data.domain.PageRequest.ofSize;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    List<Post> findAll() {
        return postRepository.findAll();
    }

    PageResponse<List<Post>> findPage(Specification<Post> specification, CursorBasedPageable pageable) {
        var postSlice = postRepository.findAll(specification, ofSize(pageable.getSize()));
        if (!postSlice.hasContent()) return new PageResponse<>(emptyList(), null, null);

        var posts = postSlice.getContent();
        return new PageResponse<>(
                posts,
                pageable.getEncodedCursor(
                        posts.get(0).getTitle(),
                        postRepository.existsByTitleLessThan(posts.get(0).getTitle())
                ),
                pageable.getEncodedCursor(
                        posts.get(posts.size() - 1).getTitle(),
                        postSlice.hasNext()
                )
        );
    }
}
