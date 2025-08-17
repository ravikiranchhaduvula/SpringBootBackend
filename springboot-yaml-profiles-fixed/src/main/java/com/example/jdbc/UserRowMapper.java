package com.example.jdbc;

import com.example.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );
        // optional: set soft-delete + roles; adjust if you don’t have these columns
        u.setDeleted(false);                 // or rs.getBoolean("deleted") if you added it
        u.setDeletedAt(null);                // or rs.getTimestamp("deleted_at") != null ? ...
        u.setUserRoles(new HashSet<>());
        return u;
    }
}