package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Task;

import java.util.List;
import java.util.UUID;

public interface UserTaskDao {

    void assignUserToTask(int userId, int taskId);
    void deleteUserFromTask(int userId, int taskId);
    List<Task> getTasksByUserId(int userId);


}
