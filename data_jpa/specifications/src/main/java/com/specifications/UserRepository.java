package com.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Arrays;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Specification<User> eligibleForCampaign = Specification.where(UserSpecifications.hasStatus("ACTIVE"))
            .and(UserSpecifications.isWithinAgeRange(18, 35))
            .and(UserSpecifications.hasInterests(Arrays.asList("books", "technology")));

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
