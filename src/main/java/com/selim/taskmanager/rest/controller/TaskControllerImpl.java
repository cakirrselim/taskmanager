package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskControllerImpl implements TaskController {
    @Override
    @PostMapping("/add")
    public ResponseEntity<TaskAddResponseModel> createTask(@RequestBody TaskAddRequestModel taskAddRequestModel) {
        return ResponseEntity.ok(new TaskAddResponseModel(taskAddRequestModel.taskName()));
    }
}
