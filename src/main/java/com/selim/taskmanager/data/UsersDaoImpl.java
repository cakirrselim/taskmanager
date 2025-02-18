package com.selim.taskmanager.data;

import com.selim.taskmanager.entitiy.Users;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    private final JdbcTemplate jdbcTemplate;

    public UsersDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Users> getAllUsers() {
        String sql = "select * from users";
        List<Users> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Users.class));
        return users;
    }

    @Override
    public Users addUser(Users user) {
        String sql = "insert into users (name, surname, username, password, mail) values (?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getMail());
            return ps;
        }, keyHolder);
        user.setId((Integer) keyHolder.getKeys().get("id"));
        return user;
    }

    @Override
    public void updateUser(Users user) {
        String sql = "update users set name = ?, surname = ?, username = ?, password = ?, mail = ?  where id = ?";
        jdbcTemplate.update(sql,
                user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getMail(), user.getId());
    }

    @Override
    public void deleteUser(int id) {
        String sql = "delete from users where id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Users findByUsername(String username) {
        String sql = "select * from users where username = ?";
        Users user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class), username);
        return user;
    }

    @Override
    public Users findByEmail(String email) {
        String sql = "select * from users where mail = ?";
        Users user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class), email);
        return user;
    }
}
