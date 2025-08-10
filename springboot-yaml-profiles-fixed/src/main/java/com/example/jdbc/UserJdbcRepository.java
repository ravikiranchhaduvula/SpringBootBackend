package com.example.jdbc;

import com.example.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbc;
    private final UserRowMapper mapper = new UserRowMapper();

    public UserJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll(int limit, int offset) {
        return jdbc.query(
                "SELECT id, name, email FROM users ORDER BY id LIMIT ? OFFSET ?",
                mapper, limit, offset
        );
    }

    public Optional<User> findById(long id) {
        var list = jdbc.query(
                "SELECT id, name, email FROM users WHERE id = ?",
                mapper, id
        );
        return list.stream().findFirst();
    }

    @Transactional
    public long create(User u) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(name, email) VALUES (?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            return ps;
        }, kh);
        return kh.getKey().longValue();
    }

    @Transactional
    public int update(User u) {
        return jdbc.update(
                "UPDATE users SET name = ?, email = ? WHERE id = ?",
                u.getName(), u.getEmail(), u.getId()
        );
    }

    @Transactional
    public int delete(long id) {
        return jdbc.update("DELETE FROM users WHERE id = ?", id);
    }
}
