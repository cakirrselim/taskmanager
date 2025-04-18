package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;
import com.selim.taskmanager.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final JdbcTemplate jdbcTemplate;
    private final RoleService roleService;

    public RoleDaoImpl(JdbcTemplate jdbcTemplate, RoleService roleService) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleService = roleService;
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

    @Override
    public void assignUserToRole(int userId, UUID roleId) {
        String sql = "INSERT INTO users_role (users_id, role_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    @Override
    public List<Users> getUsersByRoleId(UUID roleId) {
        String sql = "SELECT u.* FROM users u JOIN users_role ur ON u.id = ur.users_id WHERE ur.role_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Users.class), roleId);
    }
}
