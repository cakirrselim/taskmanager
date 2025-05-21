package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserTaskDaoImpl implements UserTaskDao {

    private JdbcTemplate jdbcTemplate;
    public UserTaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void assignRoleToUser(int userId, int taskId) {
        String sql = "INSERT INTO users_task (user_id, task_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, taskId);
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        String sql = """
            SELECT r.id, r.name
            FROM task r
            JOIN users_task ur ON r.id = ur.task_id
            WHERE ur.user_id = ?
        """;

        RowMapper<Task> mapper = (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            return task;
        };
        return jdbcTemplate.query(sql ,mapper, userId);
    }

    @Override
    public void deleteUserFromTask(int userId, int taskId) {
        String sql = "DELETE FROM users_task WHERE user_id = ? AND task_id = ?";
        jdbcTemplate.update(sql, userId, taskId);
    }
}
