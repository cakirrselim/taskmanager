package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getAllTasks();
    Task saveTask(Task task);
    void deleteTask(int id);
    void updateTask(Task task);
    List<Task> getTaskByUserId(int id);

}
