package com.cascade_type_and_orhpan_removal.repo;

import com.cascade_type_and_orhpan_removal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
