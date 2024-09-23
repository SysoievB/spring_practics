package com.spring_jdbc;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL = "select * from users";
    private static final String DELETE_ALL = "delete from users";
    private static final String INSERT_INTO = "INSERT INTO users (name, surname, date_of_birth, adult) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET name = ?, surname = ?, date_of_birth = ?, adult = ? WHERE id = ?";

    @Override
    public int save(User user) {
        return jdbcTemplate.update(INSERT_INTO,
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.isAdult(user.getDateOfBirth())
        );
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(UPDATE,
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.isAdult(user.getDateOfBirth()),
                user.getId());
    }

    @Override
    public User findById(Long id) {
        val query = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int deleteById(Long id) {
        val query = DELETE_ALL + " where id = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findByIsAdult(boolean isAdult) {
        val query = "SELECT * FROM users WHERE adult=?";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findByNameContaining(String name) {
        val query = FIND_ALL + " WHERE name LIKE '%\" + name + \"%'";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }
}
