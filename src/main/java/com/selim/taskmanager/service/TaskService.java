package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;
import com.selim.taskmanager.rest.model.TaskShowResponseModel;

import java.util.List;

public interface TaskService {

    List<TaskAddResponseModel> getAll();
    TaskAddResponseModel add(TaskAddRequestModel task);
    void delete(int id);
    void update(TaskAddRequestModel task);
    List<TaskShowResponseModel> getTaskByUserId(int userId);
}