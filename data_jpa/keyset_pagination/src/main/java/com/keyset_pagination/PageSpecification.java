package com.keyset_pagination;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class PageSpecification<T> implements Specification<T> {

    private final transient String mainFieldName;
    private final transient CursorBasedPageable cursorBasedPageable;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicate = applyPaginationFilter(root, criteriaBuilder);
        query.orderBy(criteriaBuilder.asc(root.get(mainFieldName)));

        return predicate;
    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        var searchValue = cursorBasedPageable.getSearchValue();

        return cursorBasedPageable.hasPrevPageCursor()
                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);
    }
}
