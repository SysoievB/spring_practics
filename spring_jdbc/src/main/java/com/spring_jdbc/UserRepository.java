package com.spring_jdbc;

import java.util.List;

public interface UserRepository {
    int save(User user);

    int update(Long id, User user);

    User findById(Long id);

    int deleteById(Long id);

    List<User> findAll();

    List<User> findByIsAdult(boolean isAdult);

    List<User> findByNameContaining(String title);
}
