package com.spring_jdbc;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public int update(Long id, User user) {
        return jdbcTemplate.update(UPDATE,
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.isAdult(user.getDateOfBirth()),
                id);
    }

    @Override
    public User findById(Long id) {
        val query = FIND_ALL + " WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
    }

    @Override
    public int deleteById(Long id) {
        val query = DELETE_ALL + " where id = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new UserRowMapper());
    }

    @Override
    public List<User> findByIsAdult(boolean isAdult) {
        val query = "SELECT * FROM users WHERE adult=?";
        return jdbcTemplate.query(query, new UserRowMapper(), isAdult);
    }

    @Override
    public List<User> findByNameContaining(String name) {
        val query = FIND_ALL + " WHERE name LIKE ?";
        return jdbcTemplate.query(query, new UserRowMapper(), "%" + name + "%");
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            val user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setDateOfBirth(rs.getObject("date_of_birth", LocalDate.class));
            user.setAdult(rs.getBoolean("adult"));
            return user;
        }
    }
}
