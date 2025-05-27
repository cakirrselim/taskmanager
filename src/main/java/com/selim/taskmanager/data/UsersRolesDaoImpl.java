package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public class UsersRolesDaoImpl implements UsersRolesDao {

    private final JdbcTemplate jdbcTemplate;

    public UsersRolesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void assignRoleToUser(int userId, UUID roleId) {
        String sql = "INSERT INTO users_role (users_id, role_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        String sql = """
            SELECT r.id, r.name, r.description
            FROM role r
            JOIN users_role ur ON r.id = ur.role_id
            WHERE ur.users_id = ?
        """;

        RowMapper<Role> mapper = (rs, rowNum) -> {
            Role role = new Role();
            role.setId(UUID.fromString(rs.getString("id")));
            role.setName(rs.getString("name"));
            role.setDescription(rs.getString("description"));
            return role;
        };
        return jdbcTemplate.query(sql, mapper, userId);
    }

    @Override
    public void deleteUserFromRole(int userId, UUID roleId) {
        String sql = "DELETE FROM users_role WHERE users_id = ? AND role_id = ?";
        jdbcTemplate.update(sql, userId, roleId);
    }
}
