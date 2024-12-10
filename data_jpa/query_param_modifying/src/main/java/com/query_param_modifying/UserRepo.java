package com.query_param_modifying;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user ORDER BY user.age ASC LIMIT 1", nativeQuery = true)
    Optional<User> findYoungestUser();

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname")
    List<User> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query(value = "SELECT u FROM User u ORDER BY u.id")
    Page<User> findAllUsersWithPagination(Pageable pageable);

    @Query(value = "SELECT u FROM User u WHERE u.name IN :names")
    List<User> findUserByNameList(@Param("names") Set<String> names);

    @Modifying
    @Transactional
    @Query("update User u set u.surname = :surname where u.name = :name")
    void updateUserSetSurnameForName(@Param("surname") String surname, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "insert into User (name, surname, email, age) values (:name, :surname, :email, :age)",
            nativeQuery = true)
    void insertUser(@Param("name") String name, @Param("surname") String surname,
                    @Param("email") String email, @Param("age") Integer age);

    List<User> findAllByAgeBetween(Integer minAge, Integer maxAge);
}
