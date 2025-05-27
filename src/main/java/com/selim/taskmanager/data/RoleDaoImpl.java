package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;
import com.selim.taskmanager.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final JdbcTemplate jdbcTemplate;


    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Role addRole(Role role) {
        String sql = "INSERT INTO role (name, description) VALUES (?, ?)";
        //jdbcTemplate.update(sql, role.getName(), role.getDescription());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            return ps;
    }, keyHolder);
        role.setId((UUID)keyHolder.getKeys().get("id"));
        return role;
    }

    @Override
    public void deleteRole(UUID roleId) {
        String sql = "DELETE FROM role WHERE id = ?";
        jdbcTemplate.update(sql, roleId);
    }

    @Override
    public void updateRole(Role role) {
        String sql = "UPDATE role SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                role.getName(), role.getDescription(), role.getId());
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM role";
        List<Role> role = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
        return role;
    }

    @Override
    public Role getRoleByName(String roleName) {
        String sql = "SELECT * FROM role WHERE name = ?";
        Role role = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Role.class), roleName);
        return role;
    }

    @Override
    public List<Role> getRolesByUsername(String username) {
        String sql = """
        SELECT r.id, r.name, r.description
        FROM role r
        JOIN users_role ur ON r.id = ur.role_id
        JOIN users u ON ur.users_id = u.id
        WHERE u.username = ?
    """;

        RowMapper<Role> mapper = (rs, rowNum) -> {
            Role role = new Role();
            role.setId(UUID.fromString(rs.getString("id")));
            role.setName(rs.getString("name"));
            role.setDescription(rs.getString("description"));
            return role;
        };

        return jdbcTemplate.query(sql, mapper, username);
    }



    @Override
    public Role getRoleById(UUID id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        Role role = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Role.class), id);
        return role;
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

}
