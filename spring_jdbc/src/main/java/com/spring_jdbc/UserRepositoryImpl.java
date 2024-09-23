package com.spring_jdbc;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public List<User> findByIsAdult(boolean isAdult) {
        return List.of();
    }

    @Override
    public List<User> findByNameContaining(String title) {
        return List.of();
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
