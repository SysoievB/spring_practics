package com.no_repository_bean.repo;

import com.no_repository_bean.entity.RepoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CommonAbstractRepo<T extends RepoType> extends JpaRepository<T, Long> {

    Optional<T> findByIdAndName(Long id, String name);
}
