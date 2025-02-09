package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;

import java.util.List;

public interface TaskService {

    List<TaskAddResponseModel> getAll();
    TaskAddResponseModel add(TaskAddRequestModel task);
    void delete(int id);
    void update(TaskAddRequestModel task);
    TaskAddResponseModel getById(int id);
}