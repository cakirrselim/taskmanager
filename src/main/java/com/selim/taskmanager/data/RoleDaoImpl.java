package com.selim.taskmanager.data;


import com.selim.taskmanager.entitiy.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        Role role = (Role) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), roleName);
        return role;
    }

    @Override
    public Role getRoleById(UUID id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        Role role = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Role.class), id);
        return role;
    }
}
