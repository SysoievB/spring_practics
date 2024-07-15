package com.specifications;

import jakarta.annotation.Nullable;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.specifications.User_;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.data.jpa.domain.Specification.where;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Specification<User> eligibleForCampaign = Specification.where(UserSpecifications.hasStatus("ACTIVE"))
            .and(UserSpecifications.isWithinAgeRange(18, 35))
            .and(UserSpecifications.hasInterests(Arrays.asList("books", "technology")));

    default List<User> findByCountryAndBirthDateBetween(String country, @Nullable LocalDate from, @Nullable LocalDate to) {
        val specification = where(getByCountry(country)).and(dateBetween(from, to));
        return findAll(specification);
    }

    static Specification<User> getByCountry(String country) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.country), country));
    }

    static Specification<User> dateBetween(@Nullable LocalDate from, @Nullable LocalDate to) {
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

    class UserSpecifications {

        Specification<User> spec = Specification.where(UserSpecifications.hasName("John Doe"))
                .and(UserSpecifications.hasStatus("ACTIVE"));
        public static Specification<User> hasStatus(String status) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
        }

        public static Specification<User> hasName(String name) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
        }

        public static Specification<User> hasSurname(String surname) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("surname"), surname);
        }

        public static Specification<User> isWithinAgeRange(int min, int max) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("age"), min, max);
        }

        public static Specification<User> hasInterests(List<String> interests) {
            return (root, query, criteriaBuilder) -> root.get("interests").in(interests);
        }
    }

    interface UserNameAndStatus {
        String getName();
        String getStatus();
    }
}
