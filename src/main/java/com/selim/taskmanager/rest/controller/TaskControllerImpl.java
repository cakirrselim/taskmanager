package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;
import com.selim.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;

    public TaskControllerImpl(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public ResponseEntity<TaskAddResponseModel> createTask(@RequestBody TaskAddRequestModel taskAddRequestModel) {
        TaskAddResponseModel taskAddResponseModel = taskService.add(taskAddRequestModel);
    return ResponseEntity.ok(taskAddResponseModel);
    }

    @Override
    public ResponseEntity<List<TaskAddResponseModel>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @Override
    public ResponseEntity<String> deleteTask(int id) {
        taskService.delete(id);
        return ResponseEntity.ok("Deleted task with id " + id);
    }

    @Override
    public ResponseEntity<String> updateTask(TaskAddRequestModel taskAddRequestModel) {
        taskService.update(taskAddRequestModel);
        return ResponseEntity.ok("Successfully updated task");
    }

    @Override
    public ResponseEntity<TaskAddResponseModel> getTaskById(int id) {

        return ResponseEntity.ok(taskService.getById(id));
    }


}
