package com.selim.taskmanager.data;

import com.selim.taskmanager.entitiy.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    private final JdbcTemplate jdbcTemplate;

    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM task";
        List<Task> tasks = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
        return tasks;
    }

    @Override
    public Task saveTask(Task task) {
        String sql = "INSERT INTO task (name) VALUES (?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            return ps;
        },keyHolder);
        task.setId((Integer) keyHolder.getKeys().get("id"));
        return task;
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM task WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE task SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getName(), task.getId());
    }

    @Override
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM task WHERE id = ?";
        Task task = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Task.class), id);
        return task;
    }
}
