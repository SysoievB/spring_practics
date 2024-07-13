package com.stored_procedures.named_procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    @Procedure(name = "User.GET_USERS_BY_AGE")
    List<User> getUsersByAge(@Param("ageParam") Integer age);
}
