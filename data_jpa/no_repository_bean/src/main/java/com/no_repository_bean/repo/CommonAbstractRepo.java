package com.no_repository_bean.repo;

import com.no_repository_bean.entity.RepoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CommonAbstractRepo<T extends RepoType> extends JpaRepository<T, Long> {

    Optional<T> findByIdAndName(Long id, String name);

    /**
     * <h3>Spring Data JPA supports a variable called entityName.</h3>
     * Its usage is select x from #{#entityName} x.
     * It inserts the entityName of the domain type associated with the given repository. The entityName
     * is resolved as follows: * If the domain type has set the name property on the @Entity annotation,
     * it is used. * Otherwise, the simple class-name of the domain type is used.*/
    @Query(" select T from #{#entityName} T where T.name in :names and T.birthday >= :startDateRange and T.birthday <= :endDateRange")
    List<T> findAllByNameInAndBirthdateBetweenIncluded(
            List<String> names,
            LocalDate startDateRange,
            LocalDate endDateRange
    );
}
