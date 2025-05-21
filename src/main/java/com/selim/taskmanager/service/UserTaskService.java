package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.TaskShowResponseModel;

import java.util.List;

public interface UserTaskService {

    void assignRoleToUser(int userId, int taskId);
    void deleteUserFromTask(int userId, int taskId);
    List<TaskShowResponseModel> getTasksByUserId(int userId);
}
