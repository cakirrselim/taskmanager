package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;

import org.springframework.http.ResponseEntity;


public interface TaskController {

    ResponseEntity<TaskAddResponseModel> createTask(TaskAddRequestModel taskAddRequestModel);
}
