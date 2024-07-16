package com.specifications;

import jakarta.annotation.Nullable;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.val;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    default List<User> findByCountryAndBirthDateBetween(String country, @Nullable LocalDate from, @Nullable LocalDate to) {
        val specification = where(limitByCountry(country))
                .and(dateBetween(from, to));
        return findAll(specification);
    }

    default Optional<User> findByNameAndSurnameAndAdult(String name, String surname) {
        val specification = where(limitByName(name))
                .and(limitBySurname(surname))
                .and(isTrue(User_.isAdult));
        return findOne(specification);
    }

    default Optional<User> findByNameAndCountryAndNotAdult(String name, String country) {
        val specification = where(limitByName(name))
                .and(limitByCountry(country))
                .and(not(isTrue(User_.isAdult)));
        return findOne(specification);
    }

    default List<User> findAllWithingAgeRangeInclusive(int min, int max) {
        val specification = where(isWithinAgeRange(min, max));
        return findAll(specification, Sort.by(Sort.Direction.DESC, "age"));
    }

    //specification with projection
    default List<UserNameAndSurnameProjection> getUserDtoByName(String name) {
        var specification = where(limitByName(name));
        return findBy(specification, q -> q.as(UserNameAndSurnameProjection.class).all());
    }

    private static Specification<User> limitByCountry(String country) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.country), country));
    }

    private static Specification<User> dateBetween(@Nullable LocalDate from, @Nullable LocalDate to) {
        if (isNull(from) && isNull(to)) {
            return null;
        } else if (nonNull(from) && isNull(to)) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(User_.birthDate), from));
        } else if (isNull(from)) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(User_.birthDate), to));
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get(User_.birthDate), from),
                    criteriaBuilder.lessThanOrEqualTo(root.get(User_.birthDate), to)
            ));
        }
    }

    private static Specification<User> limitByName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.name), name);
    }

    private static Specification<User> limitBySurname(String surname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.surname), surname);
    }

    private static Specification<User> isWithinAgeRange(int min, int max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(User_.age), min, max);
    }

    private static Specification<User> isTrue(SingularAttribute<User, Boolean> attribute) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(attribute));
    }
}
