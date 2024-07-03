package com.cascade_type_and_orhpan_removal.repo;

import com.cascade_type_and_orhpan_removal.entity.PrimeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimeCommentRepo extends JpaRepository<PrimeComment, Long> {
}
